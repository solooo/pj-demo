package net.solooo.demo.other.euler;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/3/17-017
 * History:
 * his1:
 */
public class SmallestMultiple {

    /*
    2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

    What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int smallest = 20;
        while (true) {
            int remainder = 0;
            for (int i = 1; i <= 20; i++) {
                remainder += smallest % i;
            }
            if (remainder == 0) {
                break;
            } else {
                smallest += 20;
            }
        }
        System.out.printf("smallest: %d cost: %d",smallest, System.currentTimeMillis() - start);
    }
}
