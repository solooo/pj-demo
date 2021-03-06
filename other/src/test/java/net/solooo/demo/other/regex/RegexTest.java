package net.solooo.demo.other.regex;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/2/28-028
 * History:
 * his1:
 */
public class RegexTest {

    @Test
    public void test1() {
        String limitRegex = "limit\\s+\\d+\\s*(?:,\\s*\\d+|)";
        Pattern p = Pattern.compile(limitRegex);
        String sql = "select * from pj limit 5  , 56    ";
        System.out.println(sql.split("limit")[0]);
        Matcher matcher = p.matcher(sql);
        if (matcher.find()) {
            System.out.println(matcher.group());
            sql = sql.replaceAll(limitRegex, "");
            System.out.println(sql);
        }

    }


    @Test
    public void messageFormat() {
        System.out.println(MessageFormat.format("{0}", 100000)); //  100,000
    }

    @Test
    public void search() {
        String str = "SELECT * FROM (select * from tt where 1=1 and (2=2) and id=(select id from ttt where 1=1 and (aaa) ) limit 5) LIMIT 0,10";
        String str2 = "SELECT * FROM tt LIMIT 0,10";
        Pattern p = Pattern.compile("(?<=\\().*(?=\\))");
        Matcher matcher = p.matcher(str2);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    public static boolean isRightCharactar(String str) {
        Pattern pattern = Pattern.compile("^[a-z][a-z0-9_]+$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    @Test
    public void check(){
        System.out.println(isRightCharactar("测试001"));
        System.out.println(isRightCharactar("ABCba测试001"));
        System.out.println(isRightCharactar("1111"));
        System.out.println(isRightCharactar("abc"));
        System.out.println(isRightCharactar("abcAbc"));
        System.out.println(isRightCharactar("abcAbc001"));
        System.out.println(isRightCharactar("abc001_"));
    }

    @Test
    public void columnType() {
        String str = "array<String(10)>";
        if (str.indexOf("<") > 0) {
            System.out.println(str.substring(0, str.indexOf("<")));
            System.out.println(str.substring(str.indexOf("<") + 1, str.length() - 1));
        }

    }
}
