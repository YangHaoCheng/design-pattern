package yhc.test.create.model;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import yhc.java.common.util.PropertiesUtil;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

public class MysqlRefereceTest  {
    private Connection connection;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Before
    public void start() throws SQLException {
        if(null == this.connection){
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yhc?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false","yhc","000000");
            String catalog = connection.getCatalog();
            System.out.println(catalog);
        }
        connection.setAutoCommit(false);
    }

    @Test
    public void insertMysql(){
       // connection.prepareStatement("insert into ")
        PropertiesUtil util = new PropertiesUtil();
        String port1 = util.getValue("config.properties", "mysql_port");
        System.out.println(port1);
        Integer mysql_port = Integer.valueOf(port1);
        System.out.println(mysql_port);
    }

    @Test
    public void testRandomInt(){
        String a = "9822000073003000030000100001";
        String newA = "";
        char[] chars = a.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            newA += chars[i];
        }
        System.out.println(newA);
    }

    @Test
    public void testDate(){
        HashMap<String,Object> map = new HashMap<>();
        Date time = (Date) map.get("time");
        System.out.println(null == time);

//        String dateStr = "Wed Jan 05 09:17:52 CST 2022";
//        String format = simpleDateFormat.format("1990-01-01 00:00:00");
//        System.out.println(format);

        //Date date = DateUtil.parse(dateStr);

        //结果：2017-03-03 22:33:23
//        Date newDate = DateUtil.offset(date, DateField.YEAR, 2);
//        //常用偏移，结果：2017-03-04 22:33:23
//        DateTime newDate2 = DateUtil.offsetDay(date, 3);
//        //常用偏移，结果：2017-03-01 19:33:23
//        DateTime newDate3 = DateUtil.offsetHour(date, -3);
//
//        java.sql.Date sqlDate = new java.sql.Date(newDate.getTime());
//
//        System.out.println(sqlDate);
    }

    @After
    public void end() throws SQLException {
        if(null == this.connection && !this.connection.isClosed()){
            this.connection.close();
        }
    }
}
