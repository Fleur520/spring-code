package com.zm.anno.jdk;

import com.zm.anno.proxy.User;
import com.zm.anno.proxy.a.UserService;
import com.zm.anno.proxy.a.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestJDKProxy {

    /*
        1. 借用类加载器  TestJDKProxy
                       UserServiceImpl
        2. JDK8.x前

            final UserService userService = new UserServiceImpl();
     */
    public static void main(String[] args) {
        //1 创建原始对象
        UserService userService = new UserServiceImpl();

        //2 JDK创建动态代理
        /*

         */

        InvocationHandler handler = new InvocationHandler(){
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("------proxy jdk befeore log  --------");
                //原始方法运行

                Object ret = method.invoke(userService, args);

                System.out.println("------proxy jdk after log --------");


                return ret;
            }
        };

        UserService userServiceProxy = (UserService)Proxy.newProxyInstance(UserServiceImpl.class.getClassLoader(),userService.getClass().getInterfaces(),handler);

        userServiceProxy.login("zhangmin", "123456");
        // userServiceProxy.register(new User());

    }
}
