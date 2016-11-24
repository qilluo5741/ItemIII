package com.sharebo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * 可以获取spring bean
 * @author niewei
 *
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		SpringContextUtil.applicationContext=ac;
	}
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	/**
	 * 通过beanName
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName){
		return applicationContext.getBean(beanName);
	}
	/**
	 *通过类Spring bean
	 * @param beanType
	 * @return
	 */
	public static  <T>T getBean(Class<T> beanType){
		return applicationContext.getBean(beanType);
	}
}
