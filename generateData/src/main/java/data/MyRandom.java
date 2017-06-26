package data;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/26
 */
public class MyRandom {

    /**
     * 指定上限int
     * @param round
     * @return
     */
    public static int getRandomInt(int round) {
        return new Random().nextInt(round);
    }

    /**
     * 指定范围Int
     * @param min
     * @param max
     * @return
     */
    public static int getRandomInt(int min, int max) {
        return min + ((int) (new Random().nextFloat() * (max - min)));
    }

    /**
     * 生成100以内float
     * @return
     */
    public static float getFloat() {
        // 取值范围1000以内
        float Max = 100, Min = 1.0f;
        BigDecimal db = new BigDecimal(Math.random() * (Max - Min) + Min);
        return db.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 计算乘积
     * @param counts
     * @param price
     * @return
     */
    public static double getDouble(int counts, float price) {
        BigDecimal pric = new BigDecimal(String.valueOf(price));
        BigDecimal count = new BigDecimal(String.valueOf(counts));
        double v = pric.multiply(count).setScale(2).doubleValue();
        return v;
    }
}
