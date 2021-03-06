package com.wgh.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.wgh.springboot.common.message.JsonResult;
import com.wgh.springboot.domain.Consumer;
import com.wgh.springboot.domain.ConsumerRecord;
import com.wgh.springboot.service.ConsumerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/consumer")
public class ConsumerController extends BaseController {
    static final Logger logger = (Logger) LoggerFactory.getLogger(ConsumerController.class);
    @Autowired
    private ConsumerService consumerService;

    @RequestMapping("/selectConsumer")
    @ResponseBody
    public JsonResult selectConsumer(@RequestBody Map requestMap) {
        JsonResult jsonResult = initJsonResult();
        List consumerList = consumerService.selectConsumer(requestMap);
        jsonResult.setRows(consumerList);
        //jsonResult.setTotal(consumerList.size());
        logger.info(JSON.toJSONString(jsonResult));
        return jsonResult;
    }

    @RequestMapping("/selectConsumerRecord")
    @ResponseBody
    public JsonResult selectConsumerRecord(@RequestBody Map requestMap) {
        JsonResult jsonResult = initJsonResult();
        List consumerRecordList = consumerService.selectConsumerRecord(requestMap);
        jsonResult.setRows(consumerRecordList);
        logger.info(JSON.toJSONString(jsonResult));
        return jsonResult;
    }

    @RequestMapping("/addConsumer")
    @ResponseBody
    public JsonResult addConsumer(@RequestBody Consumer consumer) {
        JsonResult jsonResult = initJsonResult();
        String name = consumer.getName();
        String mobile = consumer.getMobile();
        String idCard = consumer.getIdCard();
        if(StringUtils.isEmpty(name)||StringUtils.isEmpty(mobile)||StringUtils.isEmpty(idCard)){
            jsonResult.setRspCode("201");
            jsonResult.setMsg("手机号、姓名、身份证号不能为空");
            return jsonResult;
        }
        int maxMemberId = consumerService.selectMaxMemberId();
        consumer.setMemberId(maxMemberId + 1 + "");
        Boolean result = consumerService.insertConsumer(consumer);
        if (!result) {
            logger.info("/consumer/addConsumer : 新增客户失败   " + JSON.toJSONString(consumer));
            jsonResult.setRspCode("201");
            jsonResult.setMsg("新增客户失败");
        }
        return jsonResult;
    }

    @RequestMapping("/addConsumerRecord")
    @ResponseBody
    public JsonResult addConsumerRecord(@RequestBody ConsumerRecord consumerRecord) {
        JsonResult jsonResult = initJsonResult();
        String message = consumerService.insertConsumerRecord(consumerRecord);
        if(message.length()>0){
            jsonResult.setRspCode("201");
            jsonResult.setMsg(message);
        }
        return jsonResult;
    }

    @RequestMapping("/updateConsumer")
    @ResponseBody
    public JsonResult updateConsumer(@RequestBody Consumer consumer) {
        JsonResult jsonResult = initJsonResult();
        Boolean result = consumerService.updateConsumer(consumer);
        if (!result) {
            logger.info("/consumer/updateConsumer : 修改用户失败   " + JSON.toJSONString(consumer));
            jsonResult.setRspCode("201");
            jsonResult.setMsg("修改用户失败");
        }
        return jsonResult;
    }
    @RequestMapping("/updateConsumerRecord")
    @ResponseBody
    public JsonResult updateConsumerRecord(@RequestBody ConsumerRecord consumerRecord) {
        JsonResult jsonResult = initJsonResult();
        Boolean result = consumerService.updateConsumerRecord(consumerRecord);
        if (!result) {
            logger.info("/consumer/updateConsumerRecord : 修改流水失败   " + JSON.toJSONString(consumerRecord));
            jsonResult.setRspCode("201");
            jsonResult.setMsg("修改流水失败");
        }
        return jsonResult;
    }

    @RequestMapping("/deleteConsumer")
    @ResponseBody
    public JsonResult deleteConsumer(@RequestBody List<String> idList) {
        JsonResult jsonResult = initJsonResult();
        logger.info(JSON.toJSONString(idList));
        Integer count = consumerService.deleteConsumer(idList);
        if (count == 0) {
            logger.info("/consumer/deleteConsumer : 删除用户失败   " + JSON.toJSONString(idList));
            jsonResult.setRspCode("201");
            jsonResult.setMsg("删除用户失败");
        }
        return jsonResult;
    }
    @RequestMapping("/deleteConsumerRecord")
    @ResponseBody
    public JsonResult deleteConsumerRecord(@RequestBody List<String> idList) {
        JsonResult jsonResult = initJsonResult();
        logger.info(JSON.toJSONString(idList));
        Integer count = consumerService.deleteConsumerRecord(idList);
        if (count == 0) {
            logger.info("/consumer/deleteConsumerRecord : 删除流水记录失败   " + JSON.toJSONString(idList));
            jsonResult.setRspCode("201");
            jsonResult.setMsg("删除流水记录失败");
        }
        return jsonResult;
    }
}
