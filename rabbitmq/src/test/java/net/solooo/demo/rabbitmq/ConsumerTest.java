package net.solooo.demo.rabbitmq;

import net.solooo.demo.rabbitmq.consumer.MyConsumer;
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
@ContextConfiguration("classpath*:spring-rabbitmq-consumer.xml")
public class ConsumerTest {

    @Autowired
    private MyConsumer consumer;

    /**
     * Receive msg.
     *
     * @throws Exception the exception
     */
    @Test
    public void receiveMsg() throws Exception {

    }



}