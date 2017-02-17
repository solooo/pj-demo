package net.solooo.demo.other.gson;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/2/13-013
 * History:
 * his1:
 */
public class Test {

    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        method(a, b);

        System.out.println("a = " + a);
        System.out.println("b = " + b);

    }

    public static void method(int a, int b) {
        System.out.println("a = 100");
        System.out.println("b = 200");
        System.exit(0);
    }
}
