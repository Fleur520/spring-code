package com.collection;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

/**
 * @author minzhang
 * @date 2022/03/19 15:53
 **/
public class HashMapTest {

    @Test
    public  void test1(){
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
    }

    @Test
    public static void test2() throws InterruptedException {
        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
        objectObjectConcurrentHashMap.put("aaa","aaa");
        objectObjectConcurrentHashMap.get("aaa");



        ExecutorService executorService = Executors.newFixedThreadPool(1);
        IntStream.range(0, 10).forEach(i -> executorService.submit(() -> {
            try {
                objectObjectConcurrentHashMap.put("aaa",i+"====");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println(objectObjectConcurrentHashMap.get(objectObjectConcurrentHashMap));
    }


    public static void main(String[] args) throws InterruptedException {
        test2();
    }


}
