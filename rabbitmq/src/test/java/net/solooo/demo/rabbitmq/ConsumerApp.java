package net.solooo.demo.rabbitmq;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/5/25
 * History:
 * his1:
 */
public class ConsumerApp {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath*:spring-rabbitmq-consumer.xml");

    }
}
