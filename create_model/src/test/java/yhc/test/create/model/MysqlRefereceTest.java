package yhc.test.create.model;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;

public class MysqlRefereceTest  {
    private Connection connection;

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


    }

    @Test
    public void testRandomInt(){
        for (int i = 0; i < 100; i++) {
            int in = new Random().nextInt(6);

        }
    }

    @Test
    public void testDate(){
        String dateStr = "2017-03-01 22:33:23";
        Date date = DateUtil.parse(dateStr);

        //结果：2017-03-03 22:33:23
        Date newDate = DateUtil.offset(date, DateField.YEAR, 2);
        //常用偏移，结果：2017-03-04 22:33:23
        DateTime newDate2 = DateUtil.offsetDay(date, 3);
        //常用偏移，结果：2017-03-01 19:33:23
        DateTime newDate3 = DateUtil.offsetHour(date, -3);

        java.sql.Date sqlDate = new java.sql.Date(newDate.getTime());

        System.out.println(sqlDate);
    }

    @After
    public void end() throws SQLException {
        if(null == this.connection && !this.connection.isClosed()){
            this.connection.close();
        }
    }
}
