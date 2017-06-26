package net.solooo.demo.other.data;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
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
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/22
 */
public class GiveMeData {

    public static void main(String[] args) throws IOException {
        List<DataBean> dataBeanList = new GiveMeData().getAreas();
        new GiveMeData().generateData(dataBeanList);
    }

    private void generateData(List<DataBean> dataBeanList) {

        List<String> products = Arrays.asList("办公用品","家具","电子","服装","食品");
        List<String> trans = Arrays.asList("火车","大卡","空运");


        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getDefault());
        int days = 3;
        int seconds = 60 * 60 * 12;
        Random random = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
        for (int i = 0; i < days; i++) {
            Path path= Paths.get("E:/"+ sdf.format(cal.getTime()) +".txt");
            try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("utf-8"))) {
                cal.set(Calendar.HOUR_OF_DAY, 8);
                cal.set(Calendar.SECOND, 0);
                for (int j = 0; j < seconds; j++) {
                    cal.add(Calendar.SECOND, 1);
                    Date time = cal.getTime();
                    for (DataBean dataBean : dataBeanList) {
                        dataBean.setOrderid(String.valueOf(UUID.randomUUID()).replace("-", ""));
                        dataBean.setCreatetime(yyyyMMddHHmmss.format(time));
                        dataBean.setChanpinliebie(products.get(random.nextInt(5)));
                        dataBean.setYushufangshi(trans.get(random.nextInt(3)));
                        dataBean.setDanjia(getFloat()); // 单价
                        dataBean.setDingdanshuliang(random.nextInt(100));
                        dataBean.setXiaoshoue(getDouble(dataBean.getDingdanshuliang(), dataBean.getDanjia()));
                        writer.write(dataBean.toString() + "\n");
                    }
                }
                cal.add(Calendar.DAY_OF_MONTH, -1);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public float getFloat() {
        // 取值范围1000以内
        float Max = 100, Min = 1.0f;
        BigDecimal db = new BigDecimal(Math.random() * (Max - Min) + Min);
        return db.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public double getDouble(int counts, float price) {
        BigDecimal pric = new BigDecimal(String.valueOf(price));
        BigDecimal count = new BigDecimal(String.valueOf(counts));
        double v = pric.multiply(count).setScale(2).doubleValue();
        return v;
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
