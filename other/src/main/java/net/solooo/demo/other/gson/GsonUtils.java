package net.solooo.demo.other.gson;

import com.google.gson.Gson;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/6/1
 * History:
 * his1:
 */
public class GsonUtils {

    private Gson gson = new Gson();

    public String toJson(Object obj) {
        return gson.toJson(obj);
    }


}
