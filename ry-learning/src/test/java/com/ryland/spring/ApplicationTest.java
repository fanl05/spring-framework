package com.ryland.spring;

import com.ryland.spring.ctx.AppConfig;
import com.ryland.spring.ctx.TestBean;
import com.ryland.spring.ctx.import_.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Ryland
 */
@Slf4j
public class ApplicationTest {

	@Test
	public void demo01() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions(new ClassPathResource("applicationContext.xml"));
	}

	@Test
	public void demo02() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void demo03() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		TestBean testBean = (TestBean) ctx.getBean("testBean");
		log.debug("[{}]", testBean);
	}

	/**
	 * 创建 AnnotatedGenericBeanDefinition
	 */
	@Test
	public void demo04() {
		AnnotatedGenericBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(AppConfig.class);
		log.debug("[{}]", beanDefinition);
	}

	/**
	 * 编码创建 BeanDefinition
	 */
	@Test
	public void demo05() {
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
				.genericBeanDefinition(TestBean.class)
				.getBeanDefinition();
		log.debug("[{}]", beanDefinition);
	}

	/**
	 * 修改 BeanDefinition
	 */
	@Test
	public void demo06() {
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
				.genericBeanDefinition(TestBean.class)
				.getBeanDefinition();

		MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
		propertyValues.addPropertyValue("prop", "val");

		ConstructorArgumentValues constructorArgumentValues = beanDefinition.getConstructorArgumentValues();
		constructorArgumentValues.addArgumentValues(null);
	}

	/**
	 * 传入包名创建工厂
	 */
	@Test
	public void demo07() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.test");
	}

	/**
	 * 通过 Import 注解注册 Bean
	 */
	@Test
	public void demo08() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig2.class);
		TestBean2 testBean2 = ctx.getBean(TestBean2.class);
		log.debug("[{}]", testBean2);
	}

	/**
	 * ImportSelector 接口
	 */
	@Test
	public void demo09() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig3.class);
		TestBean2 bean = ctx.getBean(TestBean2.class);
		log.debug("[{}]", bean);
	}

	/**
	 * 自定义注解封装 @Import 注解
	 */
	@Test
	public void demo10() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RyConfig.class);
		TestBean2 bean = ctx.getBean(TestBean2.class);
		log.debug("[{}]", bean);
	}

	/**
	 * ImportBeanDefinitionRegistry 注册 bd
	 */
	@Test
	public void demo11() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig4.class);
		TestBean2 testBean2 = (TestBean2) ctx.getBean("testBean2");
		log.debug("[{}]", testBean2);
	}

}
