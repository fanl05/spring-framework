package com.ryland.spring.lifecycle;

import com.ryland.spring.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author Ryland
 */
@Slf4j
public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof User) {
			log.debug("postProcessBeforeInitialization");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof User) {
			User user = (User) bean;
			user.setAge(18);
			log.debug("postProcessAfterInitialization");
			return user;
		}
		return bean;
	}
}
