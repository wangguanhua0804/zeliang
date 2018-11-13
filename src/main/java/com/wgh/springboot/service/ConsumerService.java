package com.wgh.springboot.service;

import com.wgh.springboot.domain.Consumer;
import com.wgh.springboot.domain.ConsumerRecord;

import java.util.List;
import java.util.Map;

public interface ConsumerService {
    List<Consumer> selectConsumer(Map requestMap);

    int selectMaxMemberId();

    Boolean insertConsumer(Consumer consumer);

    Boolean updateConsumer(Consumer consumer);

    Integer deleteConsumer(List<String> idList);

    List selectConsumerRecord(Map requestMap);

    String insertConsumerRecord(ConsumerRecord consumerRecord);
}
