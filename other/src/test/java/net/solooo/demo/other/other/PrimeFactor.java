package net.solooo.demo.other.other;

import org.junit.Test;

/**
 * Title:Euler 3
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/3/14-014
 * History:
 * his1:
 */
public class PrimeFactor {
    private Long maxPrimeFactor(Long n) {
        if (n < 2) {
            return n;
        }
        Long i = 2L;
        while (n > i) {

            if (n%i == 0) {
                n = n/i;
            }
            i++;
        }
        return i;
    }

    @Test
    public void maxPrimeFactorTest() {
        Long n = 6008514751430L;
        System.out.println(maxPrimeFactor(n));
    }
}
