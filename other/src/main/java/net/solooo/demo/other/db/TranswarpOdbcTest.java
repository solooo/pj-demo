package net.solooo.demo.other.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/2/16-016
 * History:
 * his1:
 */
public class TranswarpOdbcTest {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Connection ct = null;
        Statement sm = null;
        try {
            //加载驱动
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

//            ct = DriverManager.getConnection("jdbc:odbc:transwarp_test", "hiveuser", "123456");

            ct= DriverManager.getConnection("jdbc:odbc:transwarp_test");

            sm = ct.createStatement();

            System.out.println(ct.getMetaData().getCatalogSeparator());
            ResultSet resultSet = sm.executeQuery("select * from pj limit 5");
            while (resultSet.next()) {
                int c1 = resultSet.getInt(1);
                int c2 = resultSet.getInt(2);
                System.out.println(c1 + "\t" + c2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (sm != null)
                    sm.close();
                if (ct != null)
                    ct.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
