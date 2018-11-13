package com.wgh.springboot.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.wgh.springboot.common.jedis.JedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class WghInterceptor implements HandlerInterceptor {
    private final static JedisUtils jedisUtils = new JedisUtils();
    Logger logger= Logger.getLogger(WghInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = httpServletRequest.getHeader("token");
        logger.info("请求地址:"+httpServletRequest.getRequestURI());
        String httpRequest=new String(httpServletRequest.getInputStream().toString().getBytes("ISO-8859-1"),"UTF-8");
        logger.info("请求报文:"+httpRequest);
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter out = null ;

        if(token==null||"".equals(token)){
            JSONObject res = new JSONObject();
            res.put("success","false");
            res.put("msg","验证失败,用户token为空");
            out = httpServletResponse.getWriter();
            out.append(res.toString());
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/to/login");
            return false;
        }
        String tokenValue = jedisUtils.getValueByKey(token);
        if(tokenValue==null){
            JSONObject res = new JSONObject();
            res.put("success","false");
            res.put("msg","验证失败,用户token已失效");
            out = httpServletResponse.getWriter();
            out.append(res.toString());
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/to/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
