package com.imooc.sm.golbal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DispatcherServlet extends GenericServlet {

    private ApplicationContext applicationContext;


    public void init() throws ServletException {
        super.init();
        applicationContext  = new ClassPathXmlApplicationContext("spring.xml");
        //容器初始化之后创建ApplicationContext工厂对象
    }


    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        //在本servlet中区分该请求是访问的那个对象中的那个方法
        /**
         * 将调用service层的功能放在一个bean对象中，因为只有在普通的bean对象中才能使用spring的
         * ioc功能对service层进行属性注入和控制反转
         * 所以在servlet中不直接调用service层，而是servlet先通过java反射的方法去调用bean对象的
         * 方法，该方法再通过属性注入和自动扫描的方式调用service层的方法，从而减轻了业务层和控制层
         * 的代码耦合
         * staff/add.do   该方法意为访问staffController下的add方法
         * login.do   特殊的请求，所以固定一个处理的bean
         * staffController就是那个bean对象
         */
        String path = request.getServletPath().substring(1);
        /**
         * getContextPath()是获取当前文件夹的路径
         * getServletPath()是获取当前文件的路径
         */
        String beanName = null;
        String methodName = null;
        int index = path.indexOf("/");
//        System.out.println(path);
        if(index != -1){
            beanName = path.substring(0,index)+"Controller";
            methodName = path.substring(index+1,path.indexOf(".do"));
        }else{
            beanName = "selfController";
            methodName = path.substring(0,path.indexOf(".do"));
        }
//        System.out.println(beanName);
        Object obj = applicationContext.getBean(beanName);//反射
        try {
            Method method = obj.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(obj,request,response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
