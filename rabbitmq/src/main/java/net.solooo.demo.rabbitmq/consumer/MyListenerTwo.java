package net.solooo.demo.rabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
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
public class MyListenerTwo implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("消息监听2:" + new String(message.getBody()));
    }
}
