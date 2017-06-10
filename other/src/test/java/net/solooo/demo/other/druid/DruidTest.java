package net.solooo.demo.other.druid;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/9
 */
public class DruidTest {

    Connection connection;

    @Before
    public void setUp() throws Exception {
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        connection = DriverManager.getConnection("jdbc:hive2://192.168.2.7:10000/default", "hive", "123456");
    }

    public void query(String sql, Helper helper) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        helper.process(resultSet);
        resultSet.close();
        stmt.close();
    }

    public interface Helper {
        void process(ResultSet rs) throws SQLException;
    }

    @Test
    public void getConn() throws SQLException {
        query("show databases", (rs) -> {
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
        });
    }

    /**
     * 普通查询
     * @throws SQLException
     */
    @Test
    public void selectBase() throws SQLException {
        long t1 = System.currentTimeMillis();
        List<Object> result = new ArrayList<>();
        query("select * from druid_table_1 limit 5", (rs) -> {
            while(rs.next()){
                result.add(rs.getString(1));
            }
        });
        System.out.println("cost: " + (System.currentTimeMillis() - t1));
        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void selectCount() throws SQLException {
        long t1 = System.currentTimeMillis();
        List<Object> result = new ArrayList<>();
        query("select count(*) from druid_table_1 limit 5", (rs) -> {
            while(rs.next()){
                result.add(rs.getInt(1));
            }
        });
        System.out.println("cost: " + (System.currentTimeMillis() - t1));
        Assert.assertEquals(result.get(0), 39244);
        result.clear();

        long t2 = System.currentTimeMillis();
        query("select count(1) from druid_table_1 limit 5", (rs) -> {
            while(rs.next()){
                result.add(rs.getInt(1));
            }
        });
        System.out.println("cost: " + (System.currentTimeMillis() - t2));
        Assert.assertEquals(result.get(0), 39244);
    }
}
