package net.solooo.demo.springboot.controller;

import net.solooo.demo.springboot.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/1/22-022
 * History:
 * his1:
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/say/{name}", method = RequestMethod.GET)
    public Map<String, Object> say(@PathVariable String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", helloService.getName());
        map.put("param", name);
        return map;
    }
}
