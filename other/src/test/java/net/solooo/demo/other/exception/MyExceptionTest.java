package net.solooo.demo.other.exception;

import org.junit.Test;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/3/10-010
 * History:
 * his1:
 */
public class MyExceptionTest {

    @Test
    public void throwExceptionTest() throws Exception {
        int i = (int) (Math.random() * 10);
        try {
            System.out.println("try body..." + i);
            if (i < 5) {
                throw new Exception("test");
            }
        } finally {
            System.out.println("finally.....");
        }
        System.out.println("end...");
    }
}
