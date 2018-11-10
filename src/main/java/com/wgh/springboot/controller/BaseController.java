package com.wgh.springboot.controller;

import com.wgh.springboot.common.message.JsonResult;

public class BaseController {
    public  JsonResult initJsonResult(){
        JsonResult jsonResult=new JsonResult();
        jsonResult.setMsg("请求成功");
        jsonResult.setRspCode("200");
        return jsonResult;
    }
}
