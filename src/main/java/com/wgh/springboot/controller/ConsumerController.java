package com.wgh.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.wgh.springboot.common.message.JsonResult;
import com.wgh.springboot.domain.Consumer;
import com.wgh.springboot.service.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/consumer")
public class ConsumerController extends BaseController {
    static final Logger logger = (Logger) LoggerFactory.getLogger(ConsumerController.class);
    @Autowired
    private ConsumerService consumerService;
    @RequestMapping("/selectConsumer")
    @ResponseBody
    public JsonResult selectConsumer(@RequestBody Map requestMap){
        JsonResult jsonResult = initJsonResult();
        List consumerList=consumerService.selectcConsumer(requestMap);
        jsonResult.setRows(consumerList);
        logger.info(JSON.toJSONString(jsonResult));
        return jsonResult;
    }
    @RequestMapping("/addConsumer")
    @ResponseBody
    public JsonResult addConsumer(@RequestBody Consumer consumer){
        JsonResult jsonResult = initJsonResult();
        int maxMemberId =consumerService.selectMaxMemberId();
        consumer.setMemberId(maxMemberId+1+"");
        Boolean result=consumerService.insertConsumer(consumer);
        if(!result){
            logger.info("/consumer/addConsumer : 插入失败   "+JSON.toJSONString(consumer));
            jsonResult.setRspCode("201");
            jsonResult.setMsg("插入失败");
        }
        return jsonResult;
    }
}
