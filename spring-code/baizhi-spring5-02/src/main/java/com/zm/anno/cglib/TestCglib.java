package com.zm.anno.cglib;

import com.zm.anno.proxy.User;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TestCglib {
    public static void main(String[] args) {
        //1 创建原始对象
        UserService userService = new UserService();

        /*
          2 通过cglib方式创建动态代理对象
            Proxy.newProxyInstance(classloader,interface,invocationhandler)

            Enhancer.setClassLoader()
            Enhancer.setSuperClass()
            Enhancer.setCallback();  ---> MethodInterceptor(cglib)
            Enhancer.create() ---> 代理
         */

        Enhancer enhancer = new Enhancer();

        enhancer.setClassLoader(TestCglib.class.getClassLoader());
        enhancer.setSuperclass(userService.getClass());

        MethodInterceptor interceptor = new MethodInterceptor() {
            //等同于 InvocationHandler --- invoke
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("---cglib start log----");

                Object ret = method.invoke(userService, args);

                System.out.println("---cglib end log----");
                return ret;
            }
        };

        enhancer.setCallback(interceptor);

        UserService userServiceProxy = (UserService) enhancer.create();

        userServiceProxy.login("zhang", "123345");
        // userServiceProxy.register(new User());
    }
}
