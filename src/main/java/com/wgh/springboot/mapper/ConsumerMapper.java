package com.wgh.springboot.mapper;

import com.wgh.springboot.domain.Consumer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConsumerMapper {
    List<Consumer> selectcConsumer(Map requestMap);

    int selectMaxMemberId();

    int insertConsumer(Consumer consumer);
}
