package com.ryland.spring.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Ryland
 */
@Data
@Slf4j
public class User implements InitializingBean, DisposableBean {

	private String name;

	private int age;

	public void init() {
		log.debug("User.init");
	}

	public void myDestroy() {
		log.debug("User.myDestroy");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("User.afterPropertiesSet");
	}

	@Override
	public void destroy() throws Exception {
		log.debug("User.destroy");
	}
}
