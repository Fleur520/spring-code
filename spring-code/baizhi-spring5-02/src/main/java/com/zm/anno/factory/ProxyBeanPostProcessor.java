package com.zm.anno.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    /*
         Proxy.newProxyInstance();
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("----- proxyBeanPostProcessor  before-----");
                Object ret = method.invoke(bean, args);
                System.out.println("----- proxyBeanPostProcessor  after-----");

                return ret;
            }
        };
      return Proxy.newProxyInstance(ProxyBeanPostProcessor.class.getClassLoader(),bean.getClass().getInterfaces(),handler);
    }
}
