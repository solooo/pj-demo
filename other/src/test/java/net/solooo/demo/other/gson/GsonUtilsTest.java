package net.solooo.demo.other.gson;

import com.google.gson.Gson;
import org.junit.Test;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/6/1
 * History:
 * his1:
 */
public class GsonUtilsTest {
    @Test
    public void toJson() throws Exception {

    }

    @Test
    public void jsonToObject() {
        String msg = "{\"command\":\"\",\"content\":\"{\\\"type\\\":\\\"DOCKER\\\",\\\"docker\\\":{\\\"image\\\":\\\"tomcat\\\",\\\"forcePullImage\\\":false,\\\"privileged\\\":false}}\"}";
        TopicMessageObject topicMessageObject = new Gson().fromJson(msg, TopicMessageObject.class);
        System.out.println(topicMessageObject.getContent());
    }

}