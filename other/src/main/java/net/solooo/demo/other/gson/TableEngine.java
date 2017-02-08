package net.solooo.demo.other.gson;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/2/7-007
 * History:
 * his1:
 */
public enum TableEngine {

    ORC("orc"), TEXT("textfile");

    private String code = "textfile";

    private TableEngine(String code) {
        this.code = code;
    }

    public static TableEngine getTableEngineByCode(String code){
        for(TableEngine engine : TableEngine.values()){
            if(engine.getCode().equals(code)){
                return engine;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
