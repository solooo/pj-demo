package net.solooo.demo.other.euler;

/**
 * Title:Euler 3<br/>
 * Description:The prime factors of 13195 are 5, 7, 13 and 29.<br/>
 * What is the largest prime factor of the number 600851475143 ?<br/>
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/3/14-014
 * History:
 * his1:
 */
public class PrimeFactor {
    private static Long maxPrimeFactor(Long n) {
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

    public static void main(String[] args) {
        Long n = 6008514751430L;
        System.out.println(maxPrimeFactor(n));
    }
}
