package net.solooo.demo.other;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.MessageFormat;

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
        double t1 = (double)91 / 100;
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
        System.out.println(Long.valueOf(1) == 1L);
    }
}
