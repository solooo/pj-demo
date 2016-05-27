package net.solooo.demo.rabbitmq.consumer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/5/25
 * History:
 * his1:
 */
@Service
public class MyConsumer {

    @Autowired
    private RabbitTemplate template;

    /**
     * Receive msg.
     */
    public void receiveMsg() {
        System.out.println(template.receiveAndConvert());
    }
}
