package net.solooo.demo.other.druid;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        connection = DriverManager.getConnection("jdbc:hive2://192.168.2.18:10000/default", "hive", "123456");
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

    /**
     * 普通查询
     * @throws SQLException
     */
    @Test
    public void selectBase() throws SQLException {
        long t1 = System.currentTimeMillis();
        List<Object> result = new ArrayList<>();
        query("select * from druid_yeji_hadoop limit 5", (rs) -> {
            while(rs.next()){
                result.add(rs.getString(1));
            }
        });
        System.out.println("selectBase() cost: " + (System.currentTimeMillis() - t1));
    }

    @Test
    public void selectCount() throws SQLException {
        // count(*) 无条件
        List<Object> result = new ArrayList<>();
        long t1 = System.currentTimeMillis();
        query("select count(*) from druid_yeji_hadoop limit 5", (rs) -> {
            while(rs.next()){
                result.add(rs.getInt(1));
            }
        });
        System.out.println("count(*) result:" + result.get(0) + " \t\t cost: " + (System.currentTimeMillis() - t1));

        // count(*) 有条件
        long t3 = System.currentTimeMillis();
        query("select count(count) from druid_yeji_hadoop where `__time`>='2017-01-01 00:00:00'", (rs) -> {
            while(rs.next()){
                result.add(rs.getInt(1));
            }
        });
        System.out.println("count(count) result: " + result.get(0) + " \t\t cost: " + (System.currentTimeMillis() - t3));

    }

    @Test
    public void groupTest() throws SQLException {
        // count(*) 无条件
        List<Object> result = new ArrayList<>();
        long t1 = System.currentTimeMillis();
        query("select jidu, sum(yeji) from druid_yeji_hadoop where `__time`>'2017-01-01 00:00:00' group by jidu ", (rs) -> {
            while(rs.next()){
                Map<String, Object> map = new HashMap<>();
                map.put(rs.getString(1), rs.getObject(2));
                result.add(map);
            }
        });
        System.out.println("groupTest() result:" + result + " \t\t cost: " + (System.currentTimeMillis() - t1));
    }

    @Test
    public void unionTest() throws SQLException {
        // count(*) 无条件
        List<Object> result = new ArrayList<>();
        long t1 = System.currentTimeMillis();
        query("select * from druid_yeji_hadoop where `__time`='2017-01-01' " +
                "union all select * from druid_yeji_hadoop where `__time`='2017-01-03'", (rs) -> {
            while(rs.next()){
                List<Object> rlist = new ArrayList<>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    rlist.add(rs.getObject(i));
                }
                result.add(rlist);
            }
        });
        System.out.println("groupTest() result:" + result + " \t\t cost: " + (System.currentTimeMillis() - t1));
    }
}
