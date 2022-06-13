package com.zm.anno.aspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zm.anno.proxy.User;

public class TestAspectProxy {


    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext2.xml");

        System.out.println("====");


        ProjectService projectService = (ProjectService) ctx.getBean("projectService");

        projectService.name("aaa");

        UserService userService = (UserService) ctx.getBean("userService");

        //userService.login("suns", "123456");
        userService.register(new User());


    }





}
