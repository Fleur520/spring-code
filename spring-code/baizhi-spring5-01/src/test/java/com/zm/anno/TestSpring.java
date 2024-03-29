package com.zm.anno;

import com.zm.anno.beanpost.Categroy;
import com.zm.anno.converter.Person;
import com.zm.anno.factorybean.ConnectionFactoryBean;
import com.zm.anno.scope.Account;
import com.zm.anno.basic.BeanFactory;
import com.zm.anno.basic.User;
import com.zm.anno.basic.UserService;
import com.zm.anno.basic.constructer.Customer;
import com.zm.anno.life.Product;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class TestSpring {
    
    /**
     *  用于测试:工厂类进行解耦合的操作
     */
    @Test
    public void test1() {
        //UserServiceImplNew

        //UserServcice userService = new UserServiceImplNew()
//        UserService userService = new UserServiceImpl();


        UserService userService = (UserService) BeanFactory.getBean("userService");

        userService.login("name", "suns");


        User user = new User("suns", "123456");
        userService.register(user);


    }


    /**
     * 用于测试:
     */
    @Test
    public void test2() {
        com.zm.anno.basic.Person person = (com.zm.anno.basic.Person) BeanFactory.getBean("person");
        System.out.println("person = " + person);
    }


    /**
     *  用于测试:用于测试Spring的第一个程序
     */
    @Test
    public void test3() {
         //1 获得Spring的工厂
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        //2 通过工厂类 获得 对象
        com.zm.anno.basic.Person person = (com.zm.anno.basic.Person) ctx.getBean("person");

        System.out.println("person = " + person);
    }

    /**
     *  用于测试:Spring工厂提供的其他方法
     */
    @Test
    public void test4() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        /*Person person = ctx.getBean("person", Person.class);
        System.out.println("person = " + person);
        */

        //当前Spring的配置文件中 只能有一个<bean class是Person类型
        /*Person person = ctx.getBean(Person.class);
          System.out.println("person = " + person);
        */

        //获取的是 Spring工厂配置文件中所有bean标签的id值  person person1
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }

        //根据类型获得Spring配置文件中对应的id值
        /*String[] beanNamesForType = ctx.getBeanNamesForType(Person.class);
        for (String id : beanNamesForType) {
            System.out.println("id = " + id);
        }
        */

        // 用于判断是否存在指定id值得bean,不能判断name值
        // 对象定义
        if (ctx.containsBeanDefinition("person")) {
            System.out.println("true = " + true);
        }else{
            System.out.println("false = " + false);
        }


        //用于判断是否存在指定id值得bean,也可以判断name值
        if (ctx.containsBean("p")) {
            System.out.println("true = " + true);
        }else{
            System.out.println("false = " + false);
        }


    }
    
   /**
    *  用于测试:spring的配置文件
    */
   @Test
   public void test5() {
       ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
       /*Person person = ctx.getBean(Person.class);
       System.out.println("person = " + person);
       */

       String[] beanDefinitionNames = ctx.getBeanDefinitionNames();

       for (String beanDefinitionName : beanDefinitionNames) {
           System.out.println("beanDefinitionName = " + beanDefinitionName);
       }
   }

   /**
    *  用于测试:用于测试name属性
    */
   @Test
   public void test6() {
       ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
       com.zm.anno.basic.Person p = (com.zm.anno.basic.Person) ctx.getBean("person");
       System.out.println("p = " + p);
   }


   /**
    *  用于测试:用于测试注入
    */
   @Test
   public void test7() {
       ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
       com.zm.anno.basic.Person person = (com.zm.anno.basic.Person) ctx.getBean("person");

       /*person.setId(1);
       person.setName("suns");*/

       System.out.println("person = " + person);
   }

   /**
    *  用于测试:通过spring的配置文件进行赋值（注入）
    */
   @Test
   public void test8() {
       ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
       com.zm.anno.basic.Person person = (com.zm.anno.basic.Person) ctx.getBean("person");

       System.out.println("person = " + person);
   }

   /**
    *  用于测试:JDK类型成员变量的赋值
    */
   @Test
   public void test9() {
       ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
       com.zm.anno.basic.Person person = (com.zm.anno.basic.Person) ctx.getBean("person");
       String[] emails = person.getEmails();
       for (String email : emails) {
           System.out.println("email = " + email);
       }
       System.out.println("--------------------------------------------------");
       Set<String> tels = person.getTels();
       for (String tel : tels) {
           System.out.println("tel = " + tel);
       }
       System.out.println("-----------------------------------------------------");
       List<String> addresses = person.getAddresses();
       for (String address : addresses) {
           System.out.println("address = " + address);
       }
       System.out.println("--------------------------------------------------");

       Map<String, String> qqs = person.getQqs();
       Set<String> keys = qqs.keySet();
       for (String key : keys) {
           System.out.println("key = " + key + " value is " + qqs.get(key));
       }
       System.out.println("-----------------------------------------------");
       Properties p = person.getP();
       System.out.println("key is key1"+ " values is "+p.getProperty("key1"));
       System.out.println("key is key2"+ " values is "+p.getProperty("key2"));
   }

   /**
    *  用于测试:用户自定义类型成员变量的赋值
    */
   @Test
   public void test10() {
       ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
       UserService userService = (UserService) ctx.getBean("userService");

       userService.register(new User("suns", "123456"));
       userService.login("xiaohei", "999999");
   }
   /**
    *  用于测试:用于测试构造注入
    */
   @Test
   public void test11() {
       ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
       Customer customer = (Customer) ctx.getBean("customer");
       System.out.println("customer = " + customer);
   }
   
   /**
    *  用于测试:用于测试FactoryBean接口 
    */
   @Test
   public void test12() {
       ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
       Connection conn = (Connection) ctx.getBean("conn");
       ConnectionFactoryBean conn2 = (ConnectionFactoryBean) ctx.getBean("&conn");
       Class<?> objectType = conn2.getObjectType();
       System.out.println("conn = " + conn);
       System.out.println("conn2 = " + conn2);
       System.out.println("conn2====objectType" + objectType);
   }

    /**
     *  用于测试:用于测试FactoryBean接口
     */
    @Test
    public void test13() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        ConnectionFactoryBean conn = (ConnectionFactoryBean) ctx.getBean("&conn");

        System.out.println("conn = " + conn);
    }


    /**
     *  用于测试:用于测试实例工厂
     */
    @Test
    public void test14() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Connection conn = (Connection) ctx.getBean("conn");
        System.out.println("conn = " + conn);
    }

    /**
     *  用于测试:简单对象的创建次数
     */
    @Test
    public void test15() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Account account1 = (Account) ctx.getBean("account");
        Account account2 = (Account) ctx.getBean("account");

        System.out.println("account = " + account1);
        System.out.println("account2 = " + account2);
    }

    /**
     *  用于测试:生命周期
     */
    @Test
    public void testScope() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Product product = (Product) ctx.getBean("product");
        //Product product2 = (Product) ctx.getBean("product");
        System.out.println(product);
        //System.out.println(product2);
        //  scope="singleton" 为true
        //System.out.println(product==product2);
        ctx.close();
    }

   /**
    *  用于测试:配置文件参数化
    */
   @Test
   public void test17() {
       ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext1.xml");
       Connection conn = (Connection) ctx.getBean("conn");
       System.out.println("conn = " + conn);
   }

   /**
    *  用于测试:用于测试自定义类型转换器
    */
   @Test
   public void test18() {
       ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext2.xml");
       Person person = (Person) ctx.getBean("person");
       System.out.println("birthday = " + person.getBirthday());
   }


    /**
     *  用于测试:BeanPostProcessor
     */
    @Test
    public void test19() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext3.xml");
        Categroy c = (Categroy) ctx.getBean("c");

        System.out.println("name = "+c.getName());
    }


    @Test
    public void test20() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext3.xml");

        BeanFactory factory = (BeanFactory)ctx;

        System.out.println(factory);
    }


    @Test
    public void test21() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("com.zm");

        //aop3  实际的spring使用aop的过程，配置好ProxyFactoryBean，给ProxyFactoryBean设置一个bean id
        //然后通过ac.getBean(bean id),就取得被ProxyFactoryBean代理的对象，不是ProxyFactoryBean
        //因为这个bean id虽然代表ProxyFactoryBean对象，直接getBean获取的是ProxyFactoryBean.getObject()返回的对象，即代理对象
        //ac.getBean(&bean id),才能取得ProxyFactoryBean对象

        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setBeanFactory(ac.getBeanFactory());
        //aop拦截处理类
        proxyFactoryBean.setInterceptorNames("myBeforeAop");
        //代理的接口
        proxyFactoryBean.setInterfaces(HelloInterface.class);
        //被代理对象
        proxyFactoryBean.setTarget(ac.getBean(HelloInterface.class));
        //放入bean工厂，实际开发是在config下使用注解，设置多个proxyFactoryBean代理，设置不同bean id
        ac.getBeanFactory().registerSingleton("myproxy",proxyFactoryBean);

        HelloInterface accountProxy = (HelloInterface) ac.getBean("myproxy");
        accountProxy.sayHello();

        Object bean1 = ac.getBean("myproxy");
        Object bean2 = ac.getBean("&myproxy");

        System.out.println(bean1);
        //获取直接的ProxyFactoryBean对象，加&
        System.out.println(bean2);

    }

































}
