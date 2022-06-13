package com.zm.javaassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @author minzhang
 * @date 2022/05/29 14:39
 **/
public class Proxy<T> {


    /**
     * 接口
     */
    private Class<T> t;

    public Proxy(Class<T> t){
        this.t = t;
    }


    /**
     * 生成的代理对象名称前缀
     */
    private static final String PROXYFREFIX = "$Proxy";
    /**
     * 生成的代理对象名称前缀
     */
    private static final String PROXYSUFFIX = "Impl";


    /*对接口--IHello进行增强，添加方法体*/
    public static void main(String[] args) throws Exception{

        ClassPool pool = ClassPool.getDefault();
        //接口实现类的名字
        Proxy<IHello> proxy = new Proxy<>(IHello.class);
        String clazzName = proxy.getPackageName() + proxy.getProxyObjectName();
        System.out.println("clazzName: " + clazzName);

        // 创建实现类 = public class com.test.cider.javassist$ProxyIHelloImpl {}
        CtClass targetClass = pool.makeClass(clazzName);

        //获得接口的CtClass  IHello.class.getName() = proxy.t.getClass().getName()
        CtClass interf = pool.getCtClass(IHello.class.getName());
        CtClass[] interfs = new CtClass[]{interf};
        // 这行代码等 = public class ProxyIHelloImpl implements IHello{}
        targetClass.setInterfaces(interfs);
        // 获取接口 sayHello 方法
        CtMethod method = interf.getDeclaredMethod("sayHello");
        // 创建方法
        CtMethod m = new CtMethod(method.getReturnType(),method.getName(),method.getParameterTypes(),targetClass);
        //设置方法的实现类
//        System.out.println("姓名: " + $1 + " 年龄: " + $2);
        m.setBody("        System.out.println(\"姓名: \" + $1 + \" 年龄: \" + $2);\n");
        targetClass.addMethod(m);
        //生成Class文件
//        targetClass.writeFile("C:\\Users\\java\\src\\main\\resources");
        //生成Class载入内存
        Class<?> target = targetClass.toClass();
        //实例化
        IHello o = (IHello) target.getDeclaredConstructor().newInstance();
        o.sayHello("weihubeats", 18);
    }

    //获取包名
    public String getPackageName(){
        Package aPackage = t.getPackage();
        return aPackage.getName();
    }

    //获取代理对象的名称
    public String getProxyObjectName(){
        return PROXYFREFIX+t.getSimpleName()+PROXYSUFFIX;
    }



}
