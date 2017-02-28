package net.solooo.demo.other.collection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

import java.util.Map;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/2/27-027
 * History:
 * his1:
 */
public class UpdateListTest {

    @Test
    public void deleteChart() {
        StringBuilder sb = new StringBuilder();
        sb.append("test").append(",");
        System.out.println(sb.toString());
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());

    }

    @Test
    public void mapToJson() {
        String json = "{\"argumentLength\":\"20\",\"colDataTypes\":\"STRING\",\"dataType\":\"String\"}";
        Map<String, String> map = JSON
                .parseObject(json, new TypeReference<Map<String, String>>(){});
        System.out.println(map);
    }
}