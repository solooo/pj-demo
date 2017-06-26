package data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/26
 */
public class GenerateData implements Runnable {

    private Integer days;

    private String rootPath;

    private Integer counts;

    private List<String> products = Arrays.asList("办公用品","家具","电子","服装","食品"); // 产品类别

    private List<String> trans = Arrays.asList("火车","大卡","空运"); // 运输方式

    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

    private DataBean dataBean;

    private CountDownLatch latch;

    public GenerateData(Integer days, String rootPath, Integer counts, DataBean dataBean, CountDownLatch latch) {
        this.days = days;
        this.rootPath = rootPath;
        this.counts = counts;
        this.dataBean = dataBean;
        this.latch = latch;
    }

    @Override
    public void run() {
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < days; i++) {
            String date = sdf.format(cal.getTime());
            String name = date + "-" + dataBean.getChengshi();
            System.out.println(name + " 数据生成...");
            generDayData(cal, dataBean, rootPath);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            System.out.println(name + " 数据生成完成");
        }
        latch.countDown();
    }

    /**
     * 生成一天内的数据
     * @param cal
     * @param dataBean
     * @param rootPath
     */
    private void generDayData(Calendar cal, DataBean dataBean, String rootPath) {
        String fileName = sdf.format(cal.getTime()) + "-" + dataBean.getChengshi();
        Path path = Paths.get(rootPath + "/" + fileName + ".txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("utf-8"))) {
            for (int n = 0; n < counts; n++) {
                cal.set(Calendar.HOUR_OF_DAY, getRandomInt(8, 20));
                cal.set(Calendar.SECOND, getRandomInt(60));

                dataBean.setOrderid(String.valueOf(UUID.randomUUID()).replace("-", ""));
                dataBean.setCreatetime(yyyyMMddHHmmss.format(cal.getTime()));
                dataBean.setChanpinliebie(products.get(getRandomInt(5)));
                dataBean.setYushufangshi(trans.get(getRandomInt(3)));
                dataBean.setDanjia(getFloat()); // 单价
                dataBean.setDingdanshuliang(getRandomInt(100));
                dataBean.setXiaoshoue(getDouble(dataBean.getDingdanshuliang(), dataBean.getDanjia()));
                writer.write(dataBean.toString() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定上限int
     * @param round
     * @return
     */
    public int getRandomInt(int round) {
        return new Random().nextInt(round);
    }

    /**
     * 指定范围Int
     * @param min
     * @param max
     * @return
     */
    public int getRandomInt(int min, int max) {
        return min + ((int) (new Random().nextFloat() * (max - min)));
    }

    /**
     * 生成100以内float
     * @return
     */
    public float getFloat() {
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
    public double getDouble(int counts, float price) {
        BigDecimal pric = new BigDecimal(String.valueOf(price));
        BigDecimal count = new BigDecimal(String.valueOf(counts));
        double v = pric.multiply(count).setScale(2).doubleValue();
        return v;
    }
}
