package com.zm.javaassist;

import com.sun.tools.javac.api.ClientCodeWrapper;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;

/**
 * @author minzhang
 * @date 2022/05/29 14:52
 **/
public class TestDemo {

    public static void  saybeforeHello(String aa) {
        System.out.println("我来了 ，before增强版" + aa);

    }

    public static void  sayAfterHello(String aa) {
        System.out.println("我来了 ，after增强版" + aa);

    }

    @Test
    public void test1 () throws Exception {
        CtClass cc;
        ClassPool pool = ClassPool.getDefault();
        cc = pool.get("com.zm.javaassist.JavaasistTest");
        CtMethod ctMethod = cc.getDeclaredMethod("sayHello");
        ctMethod.insertBefore("com.zm.javaassist.TestDemo.saybeforeHello($1);");
        ctMethod.insertAfter("com.zm.javaassist.TestDemo.sayAfterHello($1);");
        cc.toClass();
        JavaasistTest javaasistTest= new JavaasistTest();
        javaasistTest.sayHello("zhangmin");
    }
}
