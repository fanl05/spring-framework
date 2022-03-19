package com.ryland.spring.circuit.reference;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

/**
 * 测试循环引用中 先执行代理再执行属性填充会不会有问题？
 *
 * @author Ryland
 */
public class CircuitReferenceBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

	@Override
	public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
		if (bean instanceof A) {
			A a = (A) bean;
			a.getB().show();
		}
		return bean;
	}
}
