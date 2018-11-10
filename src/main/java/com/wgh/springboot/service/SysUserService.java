package com.wgh.springboot.service;

import com.wgh.springboot.common.message.JsonResult;

import java.util.Map;

public interface SysUserService {
    JsonResult login(Map<String, String> map);
}
