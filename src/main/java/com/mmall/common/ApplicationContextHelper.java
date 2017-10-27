package com.mmall.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author alone95
 * @date 2017/10/26
 */
@Component("applicationContextHelper")
public class ApplicationContextHelper implements ApplicationContextAware {

    private static  ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext =context;
    }

    public static <T> T popBean(Class<T> clazz){
        if(applicationContext ==null){
            return null;
        }
        return applicationContext.getBean(clazz);
    }

    public static <T>T popBean(String name ,Class<T>clazz){
        if(applicationContext==null){
            return null;
        }
        return applicationContext.getBean(name,clazz);
    }
}
