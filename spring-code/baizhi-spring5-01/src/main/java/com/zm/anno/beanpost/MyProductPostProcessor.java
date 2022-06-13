package com.zm.anno.beanpost;

import com.zm.anno.life.Product;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author minzhang
 * @date 2022/03/13 22:20
 **/
@Component
public class MyProductPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof Product) {
            System.out.println("Product.postProcessBefore===bean前置处理器");
            Product product = (Product) bean;
            product.setPrice(100);


        }
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof Product) {
            System.out.println("Product.postProcessAfter===bean后置处理器");
            Product product = (Product) bean;
            product.setDescription("产品名称===postProcessAfter 修改");
        }
        return bean;
    }
}
