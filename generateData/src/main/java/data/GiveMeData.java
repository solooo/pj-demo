package data;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/22
 */
public class GiveMeData {
    static int days = 3; // 生成数据天数
    static int counts = 50 * 1; // 每个城市一天内生成的数据量

    static List<String> products = Arrays.asList("办公用品","家具","电子","服装","食品"); // 产品类别
    static List<String> trans = Arrays.asList("火车","大卡","空运"); // 运输方式

    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

    final LinkedBlockingQueue<Date> calQueue = new LinkedBlockingQueue<>(20); // 一次存储20天日期
    static CountDownLatch latch = new CountDownLatch(5);
    boolean over = false;

    public static void main(String[] args) throws IOException, InterruptedException {
        List<DataBean> dataBeanList = new GiveMeData().getAreas();
        String rootPath = "E:/data";
        if (args.length > 0) {
            rootPath = args[0];
        }
        new GiveMeData().generateData(dataBeanList, rootPath);
        latch.await();
        System.out.println("---------------------执行完成-----------------------");
    }

    private void generateData(final List<DataBean> dataBeanList, final String rootPath)
            throws InterruptedException {

        // 写入日期
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeZone(TimeZone.getDefault());
                    for (int i = 0; i < days; i++) {
                        cal.add(Calendar.DAY_OF_MONTH, -1);
                        System.out.println(sdf.format(cal.getTime()));
                        calQueue.put(cal.getTime());
                    }
                    over = true;
                    latch.countDown();
                    System.out.println(Thread.currentThread().getName() + "执行完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000);
        // 取出日期生成数据
        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(calQueue.size());
                        if (calQueue.size() == 0 && over) {
                            latch.countDown();
                            System.out.println(Thread.currentThread().getName() + "执行完成");
                        } else {
                            Date cal = calQueue.take();
                            String data = sdf.format(cal);
                            System.out.println(Thread.currentThread().getName() + ": " + data + " 数据生成...");
                            GiveMeData.generDayData(cal, dataBeanList, rootPath);
                            System.out.println(Thread.currentThread().getName() + ": " + data + " 数据生成完成");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }



    }


    /**
     * 生成一天内的数据
     * @param date
     * @param dataBeanList
     * @param rootPath
     */
    private static void generDayData(Date date, List<DataBean> dataBeanList, String rootPath) {
        String fileName = sdf.format(date);
        Path path = Paths.get(rootPath + "/" + fileName + ".txt");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("utf-8"))) {
            for (int n = 0; n < counts; n++) {
                for (DataBean dataBean : dataBeanList) {
                    cal.set(Calendar.HOUR_OF_DAY, MyRandom.getRandomInt(8, 20));
                    cal.set(Calendar.SECOND, MyRandom.getRandomInt(60));

                    dataBean.setOrderid(String.valueOf(UUID.randomUUID()).replace("-", ""));
                    dataBean.setCreatetime(yyyyMMddHHmmss.format(date));
                    dataBean.setChanpinliebie(products.get(MyRandom.getRandomInt(5)));
                    dataBean.setYushufangshi(trans.get(MyRandom.getRandomInt(3)));
                    dataBean.setDanjia(MyRandom.getFloat()); // 单价
                    dataBean.setDingdanshuliang(MyRandom.getRandomInt(100));
                    dataBean.setXiaoshoue(MyRandom.getDouble(dataBean.getDingdanshuliang(), dataBean.getDanjia()));
                    writer.write(dataBean.toString() + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 找区域
     * @return
     * @throws IOException
     */
    private List<DataBean> getAreas() throws IOException {
        InputStream input = GiveMeData.class.getResourceAsStream("/city.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder sb = new StringBuilder();
        String content;
        while ((content = reader.readLine()) != null) {
            sb.append(content.trim());
        }
        String cityJson = sb.toString();
        List<Address> address = JSON.parseArray(cityJson, Address.class);
        Map<String, String> quyu = getQuyu();
        List<DataBean> dataList = new ArrayList<>(); // 所有区域

        int n = 0;
        for (Address add : address) {
            for (Address.CityBean cityBean : add.getCity()) {
                DataBean data = new DataBean();
                data.setQuyu(quyu.get(add.getName()));
                data.setShengfen(add.getName());
                data.setChengshi(cityBean.getName());
                dataList.add(data);
                if (n > 10) {
                    break;
                }
                n++;
            }
        }
        return dataList;
    }

    /**
     * 地址大区
     * @return
     */
    private Map<String, String> getQuyu() {
        Map<String, String> quyuMap = new HashMap<>();
        String huadong = "上海、江苏、浙江、安徽、福建、江西、山东、台湾";
        for (String s : huadong.split("、")) {
            quyuMap.put(s, "华东");
        }
        String huabei = "北京、天津、山西、河北、内蒙古自治区中部";
        for (String s : huabei.split("、")) {
            quyuMap.put(s, "华北");
        }
        String huazhong = "河南、湖北、湖南";
        for (String s : huazhong.split("、")) {
            quyuMap.put(s, "华中");
        }
        String huanan = "广东、广西、海南、香港特别行政区、澳门特别行政区";
        for (String s : huanan.split("、")) {
            quyuMap.put(s, "华南");
        }
        String xinan = "四川、贵州、云南、重庆、西藏";
        for (String s : xinan.split("、")) {
            quyuMap.put(s, "西南");
        }
        String xibei = "陕西、甘肃、青海、宁夏、新疆、内蒙古自治区西部";
        for (String s : xibei.split("、")) {
            quyuMap.put(s, "西北");
        }
        String dongbei = "黑龙江、吉林、辽宁、内蒙古";
        for (String s : dongbei.split("、")) {
            quyuMap.put(s, "东北");
        }
        return quyuMap;
    }

}
