package com.jdk;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author minzhang
 * @date 2021/07/05 23:47
 **/
public class JDK18Test {


    public static void main(String[] args) {

        dateApi();
        lambdaTest();
    }


    static void dateApi(){

        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        System.out.println(dtf.format(dt));
    }

    static void lambdaTest(){

        Runnable r2 = () -> System.out.println("hello lambda");
        r2.run();

    }
}
