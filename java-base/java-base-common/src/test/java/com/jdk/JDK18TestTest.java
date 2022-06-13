package com.jdk;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class JDK18TestTest {



    @Test
    public void test1() {

        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        task.run();
    }

    /**
     * 普通开启异步线程
     */
    @Test
    public void test2() {
        new Thread(
                () -> System.out.println("Hello")).start();
    }

    /**
     * 线程池开启异步线程，不接收返回参数
     */
    @Test
    public void test3() {
         ExecutorService executor = Executors.newFixedThreadPool(10);
         //executor.submit(()->method);
    }

    /**
     * 接收返回参数
     */
    @Test
    public void test4() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        //Future< ? > future = executor.submit(() -> );

    }


    @Test
    public void test5() {
        Map<String, Integer> nameMap = new HashMap<>();
        Integer value = nameMap.computeIfAbsent("name", s -> s.length());
        System.out.println(nameMap);
        System.out.println(value);


    }
}