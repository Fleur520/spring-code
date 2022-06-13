package com.gupaoedu.mvcframework.v2.servlet;

import com.gupaoedu.mvcframework.annotation.GPAutowired;
import com.gupaoedu.mvcframework.annotation.GPController;
import com.gupaoedu.mvcframework.annotation.GPRequestMapping;
import com.gupaoedu.mvcframework.annotation.GPService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class GPDispatcherSerlvet extends HttpServlet {

    //存储Bean的IoC容器声明
    private Map<String,Object> ioc = new HashMap<String,Object>();

    private Properties contextConfig = new Properties();

    private List<String> classNames = new ArrayList<String>();

    private Map<String,Method> handlerMappings = new HashMap<String, Method>();

    @Override
    public void init(ServletConfig config) throws ServletException {

        //1、加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2、读取并扫描包路径，获取相关的类
        doScanner(contextConfig.getProperty("scanPackage"));

        //3、实例化相关的类，并且将它们缓存到IoC容器之中
        doInstance();

        //4、完成依赖注入
        doAutowired();

        //5、完成HandlerMapping的映射
        doInitHandlerMapping();

        //Spring的初始化阶段完成
        System.out.println("GP Spring framework is init.");

    }

    private void doInitHandlerMapping() {

        if(ioc.isEmpty()){ return; }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {

            Class<?> clazz = entry.getValue().getClass();

            if(!clazz.isAnnotationPresent(GPController.class)){ continue; }


            String baseUrl = "";
            if(clazz.isAnnotationPresent(GPRequestMapping.class)){
                GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            //只拿public方法
            for (Method method : clazz.getMethods()) {
                if(!method.isAnnotationPresent(GPRequestMapping.class)){ continue; }

                GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);

                //demoquery
                //    //demo//query
                String url = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+","/");

                handlerMappings.put(url,method);

                System.out.println("Mapped " + url + " --> " + method);

            }
        }

    }

    private void doAutowired() {
        if(ioc.isEmpty()){ return; }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {

            Field[] fields  = entry.getValue().getClass().getDeclaredFields();

            for (Field field : fields) {

                if(!field.isAnnotationPresent(GPAutowired.class)){ continue; }

                GPAutowired autowired = field.getAnnotation(GPAutowired.class);
                String beanName = autowired.value().trim();

                if("".equals(beanName)){
                    beanName = field.getType().getName();
                }

                field.setAccessible(true); //暴力访问

                try {
                    //给entry.getValue() 的 field字段赋值，赋 ioc容器中能找到beanName的value
                    //field  = demoService
                    //entry.getValue() = demoAction
                    //beanName = com.gupaoedu.demo.service.IDemoService
                    //ioc.get(beanName) = DemoService的实例

                    //用反射执行依赖注入
                    //demoAction.demoService = ioc.get("com.gupaoedu.demo.service.IDemoService");
                    field.set(entry.getValue(), ioc.get(beanName));
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }
            }

        }
        
    }

    private void doInstance() {
        if(classNames.isEmpty()){ return; }

        try {
            for (String className : classNames) {

                Class<?> clazz = Class.forName(className);

                if(clazz.isAnnotationPresent(GPController.class)){
                    //默认情况
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    Object instance = clazz.newInstance();

                    ioc.put(beanName,instance);
                }else if(clazz.isAnnotationPresent(GPService.class)){
                    //1、默认是类名首字母小写
                    String beanName = toLowerFirstCase(clazz.getSimpleName());

                    //2、在不同包下出现两个相同的类名，自定义命名
                    GPService service = clazz.getAnnotation(GPService.class);
                    if(!"".equals(service.value())){
                        beanName = service.value();
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName,instance);

                    //3、如果扫描出来的Class是接口，接口的实现类作为value，接口的全类名作为key
                    for (Class<?> i : clazz.getInterfaces()) {
                        //如果有多个实现类
                        if(ioc.containsKey(i.getName())){
                            throw new Exception("The beanName " + i.getName() + "is exists!!");
                        }

                        ioc.put(i.getName(),instance);
                    }
                    
                }else{
                    continue;
                }



            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private String toLowerFirstCase(String simpleName) {
        char [] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    //com.gupaoedu.demo
    private void doScanner(String scanPackage) {

       URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.","/"));

       File classPath = new File(url.getFile());

        for (File file : classPath.listFiles()) {

            if(file.isDirectory()){
                doScanner(scanPackage + "." + file.getName());
            }else {
                if (!file.getName().endsWith(".class")) { continue; }
                //DemoAction.class  -->  DemoAction
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                classNames.add(className);
            }
        }

    }

    private void doLoadConfig(String contextConfigLocation)  {

        InputStream is = null;

        try {
            is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
            //读取配置文件
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //实现调用的逻辑
        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception Detail : " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String url  = req.getRequestURI();
        String contextPaht = req.getContextPath();
        url = url.replaceAll(contextPaht,"").replaceAll("/+","/");

        if(!this.handlerMappings.containsKey(url)){
            resp.getWriter().write("404 Not Found!!!");
            return;
        }

        Map<String,String[]> params = req.getParameterMap();

        Method method = this.handlerMappings.get(url);

        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());

        method.invoke(ioc.get(beanName),new Object[]{req,resp,params.get("name")[0]});

    }

}