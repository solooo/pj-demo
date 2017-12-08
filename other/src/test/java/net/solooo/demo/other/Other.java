package net.solooo.demo.other;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/3/1-001
 * History:
 * his1:
 */
public class Other {

    @Test
    public void mathTest() {
        double t1 = (double) 91 / 100;
        System.out.println(t1 + " => " + Math.ceil(t1));
    }

    @Test
    public void format() {
        String terminated = ";";
        String terminatedStr = "row format delimited fields terminated by {0}";
        terminatedStr = MessageFormat.format(terminatedStr,
                StringUtils.isBlank(terminated) ? "','" : "'" + terminated + "'");
        System.out.println(terminatedStr);
    }

    @Test
    public void reverse() {
        String str = "eric";
        String[] split = str.split("");
        String reverseStr = "";
        for (int i = split.length - 1; i >= 0; i--) {
            reverseStr += split[i];
        }

        System.out.println(reverseStr);
    }

    @Test
    public void zz() {
        int a = 128;
        int b = 128;
        Integer ai = 128;
        Integer bi = 128;
        System.out.println(a == b);
        System.out.println(Integer.toBinaryString(3000));
    }

    @Test
    public void path() {
        System.out.println(getClass().getResource("").getPath());
        System.out.println(getClass().getResource(".").getPath());
        System.out.println(getClass().getResource("/").getPath());

        String path = getClass().getResource("/").getPath();
        path = path.substring(0, path.length() - 1);
        path = path.substring(0, path.lastIndexOf("/"));
        System.out.println(path);
    }

    @Test
    public void httpRest() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate
                .getForObject("http://192.168.2.7:9200/pj.es1/_stats", String.class);
        JSONObject jsonObject = JSON.parseObject(result);
        System.out.println(JSONPath.eval(jsonObject, "$._all.total.store.size_in_bytes"));
    }

    @Test
    public void formatted() {
        String s = String.format("/%s.%s/_stats", "pj", "es");
        System.out.println(s);
    }

    @Test
    public void hexString() {
        String s = ",";
        byte[] bytes = { '\u0001' }; //s.getBytes();
        System.out.println(bytes.length);
//        StringBuilder stringBuilder = new StringBuilder("");
        char[] c = new char[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            c[i] = (char) bytes[i];
            System.out.println(String.valueOf(bytes[i] == 1));

//            String hex = Integer.toHexString(bytes[i] & 0xFF);
//
//            stringBuilder.append(hex);
        }
        System.out.println("\001".equals("\001"));
        System.out.println(StringUtils.isBlank("\001"));
    }

    @Test
    public void jdbcSearchByte() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://192.168.2.6:3306/metastore_inceptorsql1", "hiveuser",
                        "password");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement
                .executeQuery("select field_delim from tables_v where TABLE_NAME ='t1'");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("field_delim").getBytes()[0]);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void replaceTest() throws InterruptedException {
        String str = "${123} + ${123} - 2";
        String sql = "select {0} from {1} group by {0}";

        String username = str.replace("${123}", "username");
        System.out.println(username);

        System.out.println(MessageFormat.format(sql, "name", "user"));
    }

    public boolean finalTest(Integer i) {
        try {
            if (i % 2 == 0) {
                throw new Exception("test");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            System.out.println("finally....");
        }
    }

    @Test
    public void collectionsTest(){
        List<String> list = new ArrayList<>();
        list.add("test");
        list.add("hahah");
        
        System.out.println(list.isEmpty());
    }

    @Test
    public void subtest() {
        String str = "data.txt";
        System.out.println(str.substring(str.lastIndexOf(".") + 1));
    }


}
