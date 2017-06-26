package net.solooo.demo.other;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/26
 */
public class RandomTest {

    @Test
    public void random1() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(r.nextFloat());
        }
    }

    @Test
    public void random2() {
        float Max = 1000, Min = 1.0f;
        for (int i = 0; i < 100; i++) {
            BigDecimal db = new BigDecimal(Math.random() * (Max - Min) + Min);
            System.out.println(db.setScale(2, BigDecimal.ROUND_HALF_UP)// 保留30位小数并四舍五入
                    .toString());
        }
    }
}
