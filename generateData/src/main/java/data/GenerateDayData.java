package data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/26
 */
public class GenerateDayData implements Runnable {
    LinkedBlockingQueue<String> dateQueue = new LinkedBlockingQueue<>();

    private String rootPath;

    private Integer counts;

    private List<String> products = Arrays.asList("办公用品","家具","电子","服装","食品"); // 产品类别

    private List<String> trans = Arrays.asList("火车","大卡","空运"); // 运输方式

    private List<DataBean> dataBeans;

    private CountDownLatch latch;

    public GenerateDayData() {
    }

    public GenerateDayData(LinkedBlockingQueue<String> dateQueue, String rootPath, Integer counts,
            List<DataBean> dataBeans, CountDownLatch latch) {
        this.dateQueue = dateQueue;
        this.rootPath = rootPath;
        this.counts = counts;
        this.dataBeans = dataBeans;
        this.latch = latch;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (dateQueue.isEmpty()) {
                    break;
                }
                String date = dateQueue.poll(5, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + ": " + date + " 数据生成...");
                generDayData(date, dataBeans, rootPath);
                System.out.println(Thread.currentThread().getName() + ": " + date + " 数据生成完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        latch.countDown();
    }

    /**
     * 生成一天内的数据
     * @param date
     * @param dataBeans
     * @param rootPath
     */
    private void generDayData(String date, List<DataBean> dataBeans, String rootPath) {
        Path path = Paths.get(rootPath + "/" + date + ".txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("utf-8"))) {
            for (int n = 0; n < counts; n++) {
                for (DataBean dataBean : dataBeans) {
//                    System.out.println(Thread.currentThread().getName() + ": " + date);
                    String today;
                    int hour = getRandomInt(8,20);
                    today = date + " " + (hour < 10 ? "0" + hour : hour);
                    int min = getRandomInt(60);
                    today = today + ":" + (min < 10 ? "0" + min : min);
                    int sec = getRandomInt(60);
                    today = today + ":" + (sec < 10 ? "0" + sec : sec);

                    dataBean.setOrderid(String.valueOf(UUID.randomUUID()).replace("-", ""));
                    dataBean.setCreatetime(today);
                    dataBean.setChanpinliebie(products.get(getRandomInt(5)));
                    dataBean.setYushufangshi(trans.get(getRandomInt(3)));
                    dataBean.setDanjia(getFloat()); // 单价
                    dataBean.setDingdanshuliang(getRandomInt(100));
                    dataBean.setXiaoshoue(getDouble(dataBean.getDingdanshuliang(), dataBean.getDanjia()));
                    writer.write(dataBean.toString() + "\n");
                }
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

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int randomInt = new GenerateDayData().getRandomInt(8, 20);
            System.out.println(randomInt);
        }
    }
}
