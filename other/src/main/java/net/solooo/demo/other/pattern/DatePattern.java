package net.solooo.demo.other.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/2/27-027
 * History:
 * his1:
 */
public class DatePattern {

    public static void main(String[] args) {
        String regex = "(?<=-)((\\d{4}-\\d{2}-\\d{2})|MAXVALUE)";
        Pattern p = Pattern.compile(regex);
        String d1 = "2017-01-01-MAXVALUE";
        String d2 = "2016-01-01-2017-01-01";
        String d3 = "MINVALUE-2016-01-01";
        Matcher matcher = p.matcher(d3);
        if (matcher.find()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                System.out.println(matcher.group(i));
            }
        }
    }
}
