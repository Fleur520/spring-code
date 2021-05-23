package com.zm.anno;

import com.zm.anno.aop.MyAspect;
import com.zm.anno.aop.UserService;
import com.zm.anno.bean.Account;
import com.zm.anno.bean.AppConfig;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class TestAnno {

    /**
     *  用于测试:Spring的动态代理
     */
    @Test
    public void test2() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Account account = (Account) ctx.getBean("account");
        System.out.println(account.toString());
    }


    /**
     *  测试注解版本bean
     */
    @Test
    public void test3() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Account account = (Account) ctx.getBean("account");
        System.out.println(account.toString());
    }


    /**
     * 测试注解版AOP
     */
     @Test
     public void test4(){
         ApplicationContext ctx = new AnnotationConfigApplicationContext(MyAspect.class);
         UserService userService = (UserService) ctx.getBean("userServiceImpl");
         userService.login();
         Math.max(11,22);

     }
























}
