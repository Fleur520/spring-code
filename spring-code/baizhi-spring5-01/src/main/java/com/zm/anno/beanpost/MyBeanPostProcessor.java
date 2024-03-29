package com.zm.anno.beanpost;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Categroy) {
            Categroy categroy = (Categroy) bean;
            categroy.setName("xiaosg");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof Categroy) {
            Categroy categroy = (Categroy) bean;
            categroy.setName("xiaowb");
        }
        return bean;
    }
}
