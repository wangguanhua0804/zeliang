package com.wgh.springboot.service.impl;

import com.wgh.springboot.controller.ConsumerController;
import com.wgh.springboot.domain.Consumer;
import com.wgh.springboot.domain.ConsumerRecord;
import com.wgh.springboot.mapper.ConsumerMapper;
import com.wgh.springboot.service.ConsumerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    static final Logger logger = (Logger) LoggerFactory.getLogger(ConsumerServiceImpl.class);
    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public List<Consumer> selectConsumer(Map requestMap) {
        List<Consumer> consumerList = consumerMapper.selectcConsumer(requestMap);
        return consumerList;
    }


    @Override
    public int selectMaxMemberId() {
        Integer maxMemberId = consumerMapper.selectMaxMemberId();
        return maxMemberId;
    }

    @Override
    public Boolean insertConsumer(Consumer consumer) {
        try {
            Integer id = consumerMapper.insertConsumer(consumer);
            if (id > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean updateConsumer(Consumer consumer) {
        try {
            int count = consumerMapper.updateConsumer(consumer);
            if (count == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Integer deleteConsumer(List<String> idList) {
        try {
            Integer count = consumerMapper.deleteConsumer(idList);
            return count;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return 0;
        }
    }

    @Override
    public List selectConsumerRecord(Map requestMap) {
        List<ConsumerRecord> consumerRecordList = consumerMapper.selectConsumerRecord(requestMap);
        return consumerRecordList;
    }

    @Override
    public String insertConsumerRecord(ConsumerRecord consumerRecord) {
        if(consumerRecord==null){
            return "流水不能为空";
        }
        //memberId为空则创建客户并创建流水
        if(StringUtils.isEmpty(consumerRecord.getMemberId())){
            Consumer consumer=new Consumer();
            consumer.setName(consumerRecord.getName());
            consumer.setIdCard(consumerRecord.getIdCard());
            consumer.setMobile(consumerRecord.getMobile());
            consumer.setCreateUser(consumerRecord.getCreateUser());
            int maxMemberId = consumerMapper.selectMaxMemberId();
            consumer.setMemberId(maxMemberId+1+"");
            Integer insertId=consumerMapper.insertConsumer(consumer);
            if(insertId>0){
                consumerRecord.setMemberId(String.valueOf(maxMemberId));
                Boolean result =consumerMapper.insertConsumerRecord(consumerRecord);
                if (result){
                    return "";
                }
                return "新增客户成功,流水失败";
            }
            return "客户插入失败";

        }else{
            //memberId不为空则根据这个查询客户是否存在,存在的话对流水进行赋值并插入流水,不存在则返回
            Map<String,String> paramMap =new HashMap<>();
            paramMap.put("memberId",consumerRecord.getMemberId());
            List<Consumer> consumersList = consumerMapper.selectcConsumer(paramMap);
            if (consumersList.size()>0){
                Consumer consumer = consumersList.get(0);
                consumerRecord.setMobile(consumer.getMobile());
                consumerRecord.setName(consumer.getName());
                consumerRecord.setIdCard(consumer.getIdCard());
                consumerMapper.insertConsumerRecord(consumerRecord);
                return "";
            }else {
                return "会员编号不存在,请确认后输入";
            }
        }
    }

    @Override
    public Integer deleteConsumerRecord(List<String> idList) {
        try {
            Integer count = consumerMapper.deleteConsumerRecord(idList);
            return count;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return 0;
        }
    }

    @Override
    public Boolean updateConsumerRecord(ConsumerRecord consumerRecord) {
        try {
            if(consumerRecord==null||StringUtils.isEmpty(consumerRecord.getId())){
                return false;
            }
            Integer count = consumerMapper.updateConsumerRecord(consumerRecord);
            if (count == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}

