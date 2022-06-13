package com.jdk;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

/**
 * @author minzhang
 * @date 2021/07/05 23:47
 **/
public class JDK18Test {


    public static void main(String[] args) {

        //dateApi();
        //lambdaTest();
        function();

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


    static void function (){
        String s = "1234";
        change(s,(String str)->{
            return Integer.parseInt(str);

        });

    }


    public static void change(String s, Function<String,Integer> fun){
        Integer i = fun.apply(s);
        System.out.println(i);
    }

}
