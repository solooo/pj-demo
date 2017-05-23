package net.solooo.demo.other.json;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/2/7-007
 * History:
 * his1:
 */
public class App {
    public static void main(String[] args) {
        TableEngine engine = TableEngine.getTableEngineByCode("orc");
        System.out.println(engine.getCode());
    }
}
