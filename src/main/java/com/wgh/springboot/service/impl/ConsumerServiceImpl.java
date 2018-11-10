package com.wgh.springboot.service.impl;

import com.wgh.springboot.domain.Consumer;
import com.wgh.springboot.mapper.ConsumerMapper;
import com.wgh.springboot.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private ConsumerMapper consumerMapper;
    @Override
    public List seletcConsumer(Map requestMap) {
        List<Consumer> consumerList = consumerMapper.seletcConsumer(requestMap);
        return consumerList;
    }
}
