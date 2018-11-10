package com.wgh.springboot.mapper;

import com.wgh.springboot.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface SysUserMapper {
    SysUser login(Map<String, String> map);
}
