package net.solooo.demo.other.collection;

import org.junit.Test;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/2/27-027
 * History:
 * his1:
 */
public class UpdateListTest {

    @Test
    public void deleteChart() {
        StringBuilder sb = new StringBuilder();
        sb.append("test").append(",");
        System.out.println(sb.toString());
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());

    }
}