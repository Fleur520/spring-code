package com.zm.anno.life;

import com.zm.anno.beanpost.Categroy;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Data
public class Product implements InitializingBean, DisposableBean  {
    private String name;

    private String description;

    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Product.setName==set赋值");
        this.name = name;
    }

    public Product() {
        System.out.println("Product.Product无参构造器初始化");
    }

    //这个就是初始化方法：做一些初始化操作
    // Spring会进行调用
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Product.afterPropertiesSet==属性后置处理器");
    }

    public void myInit() {
        System.out.println("Product.myInit");
    }

    //销毁方法：销毁操作（资源释放的操作 ）
    @Override
    public void destroy() throws Exception {
        // scope="prototype" 不打印
        System.out.println("Product.destroy===生命周期destroy");
    }

    public void myDestroy()throws Exception{
        // scope="prototype" 不打印
        System.out.println("Product.myDestroy===自定义生命周期destroy");
    }

}
