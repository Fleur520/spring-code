package com.zm.javaassist;

/**
 * @author minzhang
 * @date 2022/05/29 14:52
 **/
public class JavaasistTest {

    public  String sayHello(String a) {
        System.out.println("原来方法也执行了，参数: " + a);
        return "1223";
    }
}
