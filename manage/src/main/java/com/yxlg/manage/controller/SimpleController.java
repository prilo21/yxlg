package com.yxlg.manage.controller;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by amosc on 5/5/2015.
 */
@Controller("simpleController")
@RequestMapping("simple/*")
public class SimpleController {

    @RequestMapping("/simple")
    public @ResponseBody String simple() {
//    	if (C2MConstants.API_VERSION_1.contains("api")) {
//			throw new NullPointerException();
//		}
//    	System.out.println(applicationContext.containsBean("ExceptionResolver"));
        return "Hello world!";
    }
    
    @Resource
    private ApplicationContext applicationContext;
}
