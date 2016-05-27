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
 * his1:
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

}