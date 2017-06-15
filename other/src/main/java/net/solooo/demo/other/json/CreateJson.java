package net.solooo.demo.other.json;

import com.alibaba.fastjson.JSON;
import net.solooo.demo.other.json.entity.SubColumn;
import net.solooo.demo.other.json.entity.SubTableInfo;

import java.util.Arrays;
import java.util.Date;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/14
 */
public class CreateJson {
    public static void main(String[] args) {
        SubTableInfo st = new SubTableInfo();
        st.setCreateTime(new Date());
        st.setCreator("Eric");
        st.setGranularity("day");
        st.setId(1);
        st.setName("tableTest");
        st.setOriginLocation("http://xxx.xxx/sss.csv");
        st.setOriginName("car_data");
        st.setTimeColumn("create_time");
        st.setTimeFormat("yyyy-MM-dd");

        SubColumn sc = new SubColumn();
        sc.setId(1);
        sc.setName("name");
        sc.setOriginName("name");
        sc.setSpec("dimensionsSpec");
        sc.setTableId(1);
        sc.setType("string");

        SubColumn sc2 = new SubColumn();
        sc2.setId(1);
        sc2.setName("age");
        sc2.setOriginName("age");
        sc2.setSpec("metricsSpec");
        sc2.setType("int");

        st.setColumns(Arrays.asList(sc, sc2));

        System.out.println(JSON.toJSONString(st, true));
    }
}
