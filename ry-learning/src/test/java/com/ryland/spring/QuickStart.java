package com.ryland.spring;

import com.ryland.spring.entity.User;
import com.ryland.spring.listener.CustomRegisteredListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Ryland
 */
@Slf4j
public class QuickStart {

	@Test
	public void demo01() {
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext01.xml"));
		User user = beanFactory.getBean(User.class);
		log.debug("[{}]", user);
	}

	/**
	 * XmlBeanFactory = DefaultListableBeanFactory + XmlBeanDefinitionReader
	 */
	@Test
	public void demo02() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		ClassPathResource resource = new ClassPathResource("applicationContext01.xml");
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		beanDefinitionReader.loadBeanDefinitions(resource);
		User user = beanFactory.getBean(User.class);
		log.debug("[{}]", user);
	}

	/**
	 * 在 alias 标签中不支持 ',' 分隔
	 * 在 bean 标签的 name 属性中支持 ',' 分隔
	 */
	@Test
	public void demo03() {
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext01.xml"));
		User u1 = beanFactory.getBean("u1", User.class);
		User u2 = beanFactory.getBean("u2", User.class);
		log.debug("[{}]", u1);
		log.debug("[{}]", u1);
		log.debug("u1 == u2: [{}]", u1 == u2);
	}

	/**
	 * event listener
	 */
	@Test
	public void demo04() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		ClassPathResource resource = new ClassPathResource("applicationContext01.xml");
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		beanDefinitionReader.setEventListener(new CustomRegisteredListener());
		beanDefinitionReader.loadBeanDefinitions(resource);
		User user = beanFactory.getBean(User.class);
		log.debug("[{}]", user);
	}

}
