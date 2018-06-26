package org.ld.bt2.util.springUtil;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Component
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtils.applicationContext == null) {
            SpringUtils.applicationContext = applicationContext;
        }
        System.out.println("--------获取applicationContext----------");
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    public static String[] controllers(Class<? extends Annotation> clazz) {
        return getApplicationContext().getBeanNamesForAnnotation(clazz);
    }

    public static <T> Map<String, T> getBeansOfType(@Nullable Class<T> var1) throws BeansException {
        return getApplicationContext().getBeansOfType(var1);
    }
}
