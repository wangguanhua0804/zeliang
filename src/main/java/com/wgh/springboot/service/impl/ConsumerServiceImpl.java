package com.wgh.springboot.service.impl;

import com.wgh.springboot.controller.ConsumerController;
import com.wgh.springboot.domain.Consumer;
import com.wgh.springboot.domain.ConsumerRecord;
import com.wgh.springboot.mapper.ConsumerMapper;
import com.wgh.springboot.service.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    static final Logger logger = (Logger) LoggerFactory.getLogger(ConsumerServiceImpl.class);
    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public List selectcConsumer(Map requestMap) {
        List<Consumer> consumerList = consumerMapper.selectcConsumer(requestMap);
        return consumerList;
    }


    @Override
    public int selectMaxMemberId() {
        int maxMemberId = consumerMapper.selectMaxMemberId();
        return maxMemberId;
    }

    @Override
    public Boolean insertConsumer(Consumer consumer) {
        try {
            int count = consumerMapper.insertConsumer(consumer);
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
}

