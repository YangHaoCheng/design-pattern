package yhc.java.flink;

import com.alibaba.fastjson.JSON;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.data.TimestampData;
import org.apache.flink.table.types.utils.TypeConversions;
import org.apache.flink.types.Row;
import org.apache.flink.util.Collector;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.apache.flink.table.api.Expressions.$;

//flink 1.12.1 rowtime遇到的问题总结：
//        对datastream进行flapmap操作时，通过returns方法指定其返回的typeinfomation，
//        typeinfomaytion信息中有包含时间属性的标示：eventtime，flapmap操作中的函数重写中，
//        无法指定与typeinfomation相对应的数据类型
//

public class TestProceTime {
    private static final List<Row> testData = new ArrayList<>();
    private static final RowTypeInfo testTypeInfo =
            new RowTypeInfo(
                    new TypeInformation[] {Types.INT, Types.LONG, Types.STRING},
                    new String[] {"a", "b", "c"});

    static {
        testData.add(Row.of(1, 1629772009L, "Hi"));
    }

    public static void main(String[] args) throws Exception{
        EnvironmentSettings.Builder streamBuilder = EnvironmentSettings.newInstance().inStreamingMode();
//        EnvironmentSettings streamSettings = streamBuilder.useBlinkPlanner().build();
        EnvironmentSettings streamSettings = streamBuilder.useOldPlanner().build();


        StreamExecutionEnvironment execEnv = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tEnv = StreamTableEnvironment.create(execEnv, streamSettings);

        DataStream<Row> srcDs = execEnv.fromCollection(testData).returns(testTypeInfo);

        srcDs = srcDs.assignTimestampsAndWatermarks(
                WatermarkStrategy.<Row>forBoundedOutOfOrderness(Duration.ofSeconds(2000))
                        .withTimestampAssigner((event,timestamp)->{
                            Long clicktime = (Long) event.getField(1);
                            if (clicktime < 10000000000L) {
                                clicktime *= 1000L;  //换算成毫秒
                            }
                            return clicktime;
                        }));

        Table in = tEnv.fromDataStream(srcDs, $("a"), $("b"), $("c"), $("rowtime").rowtime());
        tEnv.registerTable("ori", in);
        tEnv.sqlQuery("select * from ori").execute().print();
        DataStream<Row> data = tEnv.toAppendStream(in, Row.class);
        // 获取含有时间属性列的TypeInformation的信息，也是后面的datastream操作过后指定的returns类型
        TypeInformation<?>[] typeInformations = TypeConversions.fromDataTypeToLegacyInfo(in.getSchema().getFieldDataTypes());
        System.out.println(JSON.toJSON(typeInformations));


        DataStream<Row> orderedResult = data.flatMap(new FlatMapFunction<Row, Row>() {
            @Override
            public void flatMap(Row value, Collector<Row> out) throws Exception {
                Row row = new Row(4);
                Object val1 = value.getField(0);
                Object val2 = value.getField(1);
                Object val3 = value.getField(2);
                Object val4 = value.getField(3);

                System.out.println(val1.getClass().getName());
                System.out.println(val2.getClass().getName());
                System.out.println(val3.getClass().getName());
                System.out.println(val4.getClass().getName());
                System.out.println("----");

                row.setField(0, val1);
                row.setField(1, val2);
                row.setField(2, val3);

                // 第一个datastream生成的rowtime字段类型为LocalDateTime
                LocalDateTime time = (LocalDateTime)val4;

                TimestampData timestampData = TimestampData.fromLocalDateTime(time);

                Timestamp timestamp = timestampData.toTimestamp();

                //这个字段指定的数据类型，根据指定的outputtype解析不出来

//                new TimestampData(time.toInstant(ZoneOffset.of("+8")).toEpochMilli(),3);
//                java.sql.Time sqlTime = new java.sql.Time(time.toInstant(ZoneOffset.of("+8")).toEpochMilli());
//                row.setField(3, timestamp);
                row.setField(3, 1629772009000L);

                try {
                    out.collect(row);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

// outputtype:json字符串 [{"arity":1,"basicType":true,"totalFields":1,"typeClass":"java.lang.Integer","tupleType":false,"genericParameters":{},"keyType":true,"sortKeyType":true},{"arity":1,"basicType":true,"totalFields":1,"typeClass":"java.lang.Long","tupleType":false,"genericParameters":{},"keyType":true,"sortKeyType":true},{"arity":1,"basicType":true,"totalFields":1,"typeClass":"java.lang.String","tupleType":false,"genericParameters":{},"keyType":true,"sortKeyType":true},{"arity":1,"basicType":false,"totalFields":1,"eventTime":true,"typeClass":"java.sql.Timestamp","tupleType":false,"genericParameters":{},"keyType":true,"sortKeyType":true}]
        orderedResult.getTransformation().setOutputType(new RowTypeInfo(typeInformations));

        Table rpTable = tEnv.fromDataStream(orderedResult,$("a"), $("b"), $("c"), $("rowtime"));


        tEnv.registerTable("rpTable", rpTable);
        tEnv.sqlQuery("select * from rpTable").execute().print();

        execEnv.execute("");



    }
}


