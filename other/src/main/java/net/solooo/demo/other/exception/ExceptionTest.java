package net.solooo.demo.other.exception;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/2/17-017
 * History:
 * his1:
 */
public class ExceptionTest {
    public void thex() throws Exception {
        Exception ex = null;
        try {
            System.out.println("step 1...");
            throw new Exception("custom exception");
        } catch (Exception e) {
            ex = e;
        } finally {
            System.out.println("step 2...");
        }
        if (ex != null) {
            throw new Exception(ex);
        }
    }

    public static void main(String[] args) {
        try {
            new ExceptionTest().thex();
        } catch (Exception e) {
            System.out.println("step 3...");
            System.out.println(e.getMessage());
        }
    }
}