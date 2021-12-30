package yhc.java.flink.cdc.mysql2hive;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * @author ：_my
 * @date ：Created in 2021/12/15 15:05
 * @description：My test
 * @version: 1.0
 */
public class MyMysql2Hive {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        EnvironmentSettings settings = EnvironmentSettings.newInstance().inStreamingMode().useBlinkPlanner().build();
        StreamTableEnvironment tableEnvironment = StreamTableEnvironment.create(env, settings);
        // source ddl
        tableEnvironment.executeSql("");
        String ddl ="create Table yhc_user (" +
                "" +
                "" +
                "" +
                ")";


    }
}
