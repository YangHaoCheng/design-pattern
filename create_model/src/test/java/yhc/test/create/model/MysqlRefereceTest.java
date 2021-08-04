package yhc.test.create.model;

import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlRefereceTest  {
    private Connection connection;

    @Before
    public void start() throws SQLException {
        if(null == this.connection){
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yhc?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false");
            String catalog = connection.getCatalog();
            System.out.println(catalog);
        }
        connection.setAutoCommit(false);
    }

    @Test
    public void insertMysql(){
       // connection.prepareStatement("insert into ")


    }

}
