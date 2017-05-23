package net.solooo.demo.other.json;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.junit.Test;

/**
 * Description:
 * Author:Eric
 * Date:2017/5/23
 */
public class JsonPathTest {

    @Test
    public void path() {
        String json = "{\n" +
                "\t\"result\": [{\n" +
                "\t\t\"eventid\": \"6\",\n" +
                "\t\t\"hosts\": [{\n" +
                "\t\t\t\"name\": \"Zabbix server\",\n" +
                "\t\t\t\"hostid\": \"10084\"\n" +
                "\t\t}]\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"eventid\": \"51\",\n" +
                "\t\t\"hosts\": [{\n" +
                "\t\t\t\"name\": \"Zabbix server\",\n" +
                "\t\t\t\"hostid\": \"10084\"\n" +
                "\t\t}]\n" +
                "\t}],\n" +
                "\t\"id\": 19,\n" +
                "\t\"jsonrpc\": \"2.0\"\n" +
                "}";
        Object jsonObj = JSONObject.parse(json);
        Object result = JSONPath.eval(jsonObj, "$.result..hostid");
        System.out.println(result);
    }

}
