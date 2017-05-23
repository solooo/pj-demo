package net.solooo.demo.other.json;

import java.util.ArrayList;
import java.util.List;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/2/7-007
 * History:
 * his1:
 */
public class ListTest {

    public static void removeTest() {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            list.add("test" + i);
        }
        for (int i = 5; i > 0; i--) {
            list.add("test" + i);
        }

        System.out.println(list);

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j!=i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }

        System.out.println(list);
    }

    public static void main(String[] args) {
        ListTest.removeTest();
    }
}
