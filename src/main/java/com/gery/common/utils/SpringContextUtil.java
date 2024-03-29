package com.gery.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     *
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取对象
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /***
     * @Description: 通过name,以及Clazz返回指定的Bean
     *
     * @Param: [name, clazz]
     * @return: T
     * @Author: ZhangJingYuan
     * @Date: 2022/1/7
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
