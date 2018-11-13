package com.wgh.springboot.service.impl;

import com.wgh.springboot.common.jedis.JedisUtils;
import com.wgh.springboot.common.message.JsonResult;
import com.wgh.springboot.domain.SysResource;
import com.wgh.springboot.domain.SysRole;
import com.wgh.springboot.domain.SysUser;
import com.wgh.springboot.mapper.SysUserMapper;
import com.wgh.springboot.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
                String  uuid = UUID.randomUUID().toString();
                JedisUtils jedisUtils=new JedisUtils();
                jedisUtils.setKeyValueWithTimeOut(sysUser.getUser(),uuid,60*60*24l);
                jedisUtils.setKeyValueWithTimeOut(uuid,sysUser.getUser(),60*60*24l);
                sysUser.setToken(uuid);
                SysRole sysRole=sysUserMapper.selectRoleById(sysUser.getId());
                List<SysResource> sysResourceList=sysUserMapper.selectResourceByRoleId(sysRole.getId());
                sysRole.setResourceList(sysResourceList);
                sysUser.setRole(sysRole);
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

    @Override
    public JsonResult logout(Map<String, String> map) {
        JsonResult jsonResult = new JsonResult();
        String token=map.get("token");
        JedisUtils jedisUtils = new JedisUtils();
        String userId = jedisUtils.getValueByKey(token);
        jedisUtils.delKey(userId);
        jedisUtils.delKey(token);
        jsonResult.setRspCode("200");
        jsonResult.setMsg("成功退出");
        return jsonResult;
    }
}
