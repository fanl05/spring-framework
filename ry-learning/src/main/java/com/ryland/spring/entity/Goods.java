package com.ryland.spring.entity;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;

/**
 * @author Ryland
 */
@Data
public class Goods implements BeanNameAware, BeanFactoryAware {

	private int sizeId;

	private String name;

	private String brandSn;

	private String goodsBeanName;

	private BeanFactory myBeanFactory;

	/**
	 * @param name 在容器种的名字
	 */
	@Override
	public void setBeanName(String name) {
		goodsBeanName = name;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		myBeanFactory = beanFactory;
	}
}
