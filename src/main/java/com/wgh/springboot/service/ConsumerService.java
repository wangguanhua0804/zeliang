package com.wgh.springboot.service;

import com.wgh.springboot.domain.Consumer;

import java.util.List;
import java.util.Map;

public interface ConsumerService {
    List selectcConsumer(Map requestMap);

    int selectMaxMemberId();

    Boolean insertConsumer(Consumer consumer);

    Boolean updateConsumer(Consumer consumer);

    Integer deleteConsumer(List<String> idList);
}
