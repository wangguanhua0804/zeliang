package com.wgh.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.wgh.springboot.common.message.JsonResult;
import com.wgh.springboot.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
@RequestMapping("/sys")
@Controller
public class SysController extends BaseController {

    static final Logger logger = (Logger) LoggerFactory.getLogger(SysController.class);
    @Autowired
    private SysUserService sysUserService;


    @ResponseBody
    @RequestMapping("/login")
    public JsonResult login(@RequestBody Map<String,String> map){
        JsonResult jsonResult = sysUserService.login(map);
        logger.info(JSON.toJSONString(jsonResult));
        return jsonResult;
    }
}
