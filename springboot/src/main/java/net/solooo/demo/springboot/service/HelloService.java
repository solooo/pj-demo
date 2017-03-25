package net.solooo.demo.springboot.service;

import net.solooo.demo.springboot.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AppConfig config;

    public String getName() {
        return config.getName();
    }
}
