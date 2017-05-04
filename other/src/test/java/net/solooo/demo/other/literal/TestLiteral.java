package net.solooo.demo.other.literal;

import org.junit.Test;

/**
 * Description:
 * Author:Eric
 * Date:2017/5/4
 */
public class TestLiteral {

    @Test
    public void toStringTest() {
        Hello hello = new Hello();
        int i = 5;
        while (i > 0) {
            hello.setName("name" + i);
            System.out.println(hello.toString());
            i--;
        }
    }

    @Test
    public void asciiToString() {
        int a= 97;
        System.out.println((char) a);
    }
}
