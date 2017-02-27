package net.solooo.demo.other.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/2/27-027
 * History:
 * his1:
 */
public class UpdateList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        list.add("test4");

        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + "##");
        }
        System.out.println(list);
    }
}
