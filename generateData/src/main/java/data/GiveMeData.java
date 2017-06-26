package data;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/22
 */
public class GiveMeData {

    CountDownLatch latch;

    /**
     *
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        List<DataBean> dataBeanList = new GiveMeData().getAreas();
        int days = 3; // 生成数据天数
        int dayCounts = 50 * 10000; // 每天生成的数据量
        if (args[0] != null && args[0].length() > 0) {
            dayCounts = Integer.parseInt(args[0]);
        }
        if (args[1] != null && args[1].length() > 0) {
            days = Integer.parseInt(args[1]);
        }
        System.out.println("此任务数据量：每天" + dayCounts + "条数据，共" + days + "天");
        String rootPath = "data";
        new GiveMeData().generateData(dayCounts, days, dataBeanList, rootPath);
        System.out.println("---------------------执行完成-----------------------");
    }

    private void generateData(Integer dayCounts, Integer days, List<DataBean> dataBeanList, String rootPath)
            throws InterruptedException {

        System.out.println(dataBeanList.size() + " 个城市");
        latch = new CountDownLatch(dataBeanList.size());
        for (DataBean dataBean : dataBeanList) {
            new Thread(new GenerateData(days, rootPath, dayCounts, dataBean, latch)).start();
        }
        latch.await();
    }

    private void generateDayData() {

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
