package com.base.function;

import java.util.function.Predicate;

/**
 * @author minzhang
 * @date 2022/01/16 17:19
 **/
public class FunctionTest {


    public static void main(String[] args) {
        //接收一个泛型参数，比较返回boolean值
        Predicate<String> predicate = (v1) -> {
            //此处别的逻辑处理，做简单的语法介绍就不详细写了。
            return v1.equals("1");
        };
//        Predicate<String> predicates = (v1) -> v1.equals("2"); 直接返回结果可这样写

        System.out.println(predicate.test("1"));//true
        System.out.println(predicate.test("2"));//false


    }



}
