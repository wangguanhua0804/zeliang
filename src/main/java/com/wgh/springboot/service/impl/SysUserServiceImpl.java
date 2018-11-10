package com.wgh.springboot.service.impl;

import com.wgh.springboot.common.message.JsonResult;
import com.wgh.springboot.domain.SysUser;
import com.wgh.springboot.mapper.SysUserMapper;
import com.wgh.springboot.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public JsonResult login(Map<String, String> map) {
        JsonResult jsonResult = new JsonResult();
        SysUser sysUser = sysUserMapper.login(map);
        if (sysUser != null) {
            if (map.get("password").equals(sysUser.getPassword())) {
                jsonResult.setRspCode("200");
                jsonResult.setMsg("登录成功");
                jsonResult.setBody(sysUser);
                return jsonResult;
            }else{
                jsonResult.setRspCode("201");
                jsonResult.setMsg("密码错误");
                return jsonResult;
            }
        }
        jsonResult.setRspCode("201");
        jsonResult.setMsg("账号不存在");
        return jsonResult;
    }
}
