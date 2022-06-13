package com.zm.anno.aop;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author minzhang
 * @date 2022/03/13 21:20
 **/
@Component
public class MyBeforeAop implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("before aop ["+method.getName()+"] do sth...................");
    }
}
