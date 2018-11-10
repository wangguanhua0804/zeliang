package com.wgh.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/to")
@Controller
public class ToPageControllrt extends BaseController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/home")
    public String home(){
        return "home";
    }
    @RequestMapping("/head")
    public String head(){
        return "head";
    }
    @RequestMapping("/consumerInfo")
    public String consumerInfo(){
        return "consumerInfo";
    }
    @RequestMapping("/consumerRecord")
    public String consumerRecord(){
        return "consumerRecord";
    }

}
