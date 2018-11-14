package com.wgh.springboot.mapper;

import com.wgh.springboot.domain.Consumer;
import com.wgh.springboot.domain.ConsumerRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConsumerMapper {
    List<Consumer> selectcConsumer(Map requestMap);

    Integer selectMaxMemberId();

    Integer insertConsumer(Consumer consumer);

    Integer updateConsumer(Consumer consumer);

    Integer deleteConsumer(List<String> idList);

    List<ConsumerRecord> selectConsumerRecord(Map requestMap);

    Boolean insertConsumerRecord(ConsumerRecord consumerRecord);

    Integer deleteConsumerRecord(List<String> idList);

    Integer updateConsumerRecord(ConsumerRecord consumerRecord);
}
