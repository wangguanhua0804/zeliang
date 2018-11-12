package com.wgh.springboot.mapper;

import com.wgh.springboot.domain.SysResource;
import com.wgh.springboot.domain.SysRole;
import com.wgh.springboot.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUserMapper {
    SysUser login(Map<String, String> map);

    SysRole selectRoleById(@Param("userId") String userId);

    List<SysResource> selectResourceByRoleId(@Param("roleId")String roleId);
}
