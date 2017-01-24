package net.solooo.demo.springboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/1/23-023
 * History:
 * his1:
 */
@Service
public class HelloService {

    @Value("${app.name}")
    private String appName;

    public String getName() {
        return appName;
    }
}
