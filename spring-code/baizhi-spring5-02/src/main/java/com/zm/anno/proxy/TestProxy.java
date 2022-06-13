package com.zm.anno.proxy;

import com.zm.anno.proxy.a.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestProxy {


    public static void main(String[] args) {
        // ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext2.xml");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        UserService userService = (UserService) ctx.getBean("userService");

        //userService.login("suns", "123456");
        userService.register(new User());

        userService.login("aa","bbb");
    }





}
