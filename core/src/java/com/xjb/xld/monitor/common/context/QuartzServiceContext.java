/**
 * 
 */
package com.xjb.xld.monitor.common.context;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title: QuartzServiceContext.java
 * @Description: 缓存job 的spring 上下文
 * @author yunfei.zang
 * @date 2014年5月7日 下午3:18:48
 */
public class QuartzServiceContext {

	private static ApplicationContext context;

	static {
		getCtx();
	}

	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(ApplicationContext context) {
		QuartzServiceContext.context = context;
	}

	/**
	 * 
	 * @return
	 */
	public static PropertyPlaceholderConfigurer getConfigProperties() {
		if (context != null) {
			return (PropertyPlaceholderConfigurer) context
					.getBean("placeholderConfig");
		}
		return null;
	}

	private static ApplicationContext getCtx() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(
					"classpath*:spring/spring-taskapp.xml");
		}
		return context;
	}
}
