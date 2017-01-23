package net.solooo.demo.rabbitmq;

import net.solooo.demo.rabbitmq.producer.MyProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/5/25
 * History:
 * his1:+
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-rabbitmq-producer.xml")
public class ProducerTest {


    @Autowired
    private MyProducer myProducer;

    /**
     * Send msg.
     *
     * @throws Exception the exception
     */
    @Test
    public void sendMsg() throws Exception {
        for (int i = 0; i < 10000; i++) {
            myProducer.sendMsg("test message "+ i);
            Thread.sleep(50);
        }
    }

    @Test
    public void sendMsgToTopic() throws Exception {
        String createApp = "{\"mem\":256,\"id\":\"tomcat2\",\"cpus\":1,\"instances\":2,\"container\":{\"type\":\"DOCKER\",\"docker\":{\"image\":\"tomcat\",\"forcePullImage\":false,\"privileged\":false}},\"disk\":10}";
        myProducer.sendMsgToTopic("37984aebaa2947109beb06b6b4d89180", createApp);

    }

}