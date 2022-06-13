package com.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.vo.Person;
import org.junit.jupiter.api.Test;

/**
 * @author minzhang
 * @date 2021/07/05 22:44
 **/
public class ReflectTest {

    public static void main(String[] args) throws Exception {

    }


    @Test
    public void getClassTest() throws ClassNotFoundException {

        Class booletClass = boolean.class;
        System.out.println(booletClass);
        Class intClass =int.class;
        System.out.println(intClass);

        Class p = Person.class;
        // 第三种方式
        Person person = new Person();
        Class c = person.getClass();

        // 第四种
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class clazz4 = classLoader.loadClass("com.mysql.cj.jdbc.MysqlDataSource");
        System.out.println(clazz4.getName());

    }


    @Test
    public  void getMethod() throws Exception {

        Class clazz = Class.forName("com.mysql.cj.jdbc.MysqlDataSource");

        // 获得所有构造方法
        Constructor[] constructors = clazz.getDeclaredConstructors();
        System.out.println("------获取类中的构造方法-----------");
        Arrays.stream(constructors).forEach(obj -> {
            System.out.println(obj);
        });

        Method[] methods = clazz.getDeclaredMethods();
        System.out.println("------获取类中定义的方法、修饰符-----------");
        for (Method method : methods) {
            System.out.println(method.getName());
            System.out.println(method.getModifiers());
        }
        System.out.println("-------获取公有的属性------------");
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        System.out.println("-------获取所有的属性------------");
        Field[] pubFields = clazz.getDeclaredFields();
        for (Field pubField : pubFields) {
            pubField.setAccessible(true);
            System.out.println(pubField.getName());
        }

    }

}
