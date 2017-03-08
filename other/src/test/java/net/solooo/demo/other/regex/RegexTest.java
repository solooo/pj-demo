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
        System.out.println(MessageFormat.format("select count(1) from ({0})", "select * from pj"));
    }

    public void search() {
        Pattern p = Pattern.compile("use|");
    }
}
