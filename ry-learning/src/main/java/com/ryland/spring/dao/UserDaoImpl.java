package com.ryland.spring.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author Ryland
 */
@Slf4j
public class UserDaoImpl implements UserDao, BeanFactoryAware {

	private BeanFactory beanFactory;

	@Override
	public void save() {
		UserDao userDao = beanFactory.getBean(UserDao.class);
		log.debug("this: [{}], getBean: [{}]", this, userDao);
		log.debug("registered...");
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
}
