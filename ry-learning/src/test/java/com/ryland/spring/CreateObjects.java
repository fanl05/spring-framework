package com.ryland.spring;

import com.ryland.spring.config.ProductConfig;
import com.ryland.spring.dao.UserDao;
import com.ryland.spring.entity.*;
import com.ryland.spring.service.ProductService;
import com.ryland.spring.service.UserService;
import com.ryland.spring.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Ryland
 */
@Slf4j
public class CreateObjects {

	/**
	 * 通过静态工厂或实例工厂创建对象
	 */
	@Test
	public void demo01() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext03.xml");
		Object bean1 = applicationContext.getBean("bean1");
		Object bean2 = applicationContext.getBean("bean2");
		log.debug("[{}]", bean1);
		log.debug("[{}]", bean2);
	}

	@Test
	public void demo02() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext04.xml");
		Goods goods = applicationContext.getBean(Goods.class);
		log.debug("[{}]", goods);
		log.debug("[{}]", goods.getMyBeanFactory() == applicationContext);
	}

	@Test
	public void demo03() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		ClassPathResource resource = new ClassPathResource("applicationContext04.xml");
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		beanDefinitionReader.loadBeanDefinitions(resource);
		Goods goods = beanFactory.getBean(Goods.class);
		log.debug("[{}]", goods);
		log.debug("[{}]", goods.getMyBeanFactory() == beanFactory);
	}

	/**
	 * 若 userDao 是线程不安全的，有两种解决方式
	 * 1. 加锁
	 * 2. 多例（推荐）
	 * <p>
	 * 注入的过程种指定 scope 为 prototype 会失效
	 */
	@Test
	public void demo04() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext05.xml");
		UserService userService = applicationContext.getBean(UserService.class);
		userService.register();
		userService.register();
		UserDao userDao1 = applicationContext.getBean(UserDao.class);
		UserDao userDao2 = applicationContext.getBean(UserDao.class);
		log.debug("[{}]", userDao1 == userDao2);
	}

	/**
	 * 先调用 afterPropertiesSet
	 */
	@Test
	public void demo05() {
		XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext06.xml"));
		User user = beanFactory.getBean(User.class);
		beanFactory.destroySingletons();
	}

	/**
	 * 只有高级别工厂才会调用 BeanPostProcessor
	 */
	@Test
	public void demo06() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext06.xml");
		User user = applicationContext.getBean(User.class);
		log.debug("[{}]", user);
	}

	/**
	 * 以 & 开头且不是 FactoryBean
	 * 抛出 BeanIsNotAFactoryException
	 */
	@Test
	public void demo07() {
		XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext06.xml"));
		User user = (User) beanFactory.getBean("&u2");
		log.debug("[{}]", user);
	}

	/**
	 * 父子容器问题
	 * 实战中的父子容器：SpringMVC
	 * child: DispatcherServlet
	 * root: ContextLoaderListener
	 * <p>
	 * 若有相同配置，以子容器为准
	 */
	@Test
	public void demo08() {
		// 父容器
		DefaultListableBeanFactory parent = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader definitionReader1 = new XmlBeanDefinitionReader(parent);
		definitionReader1.loadBeanDefinitions(new ClassPathResource("applicationContext-parent.xml"));

		// 子容器
		DefaultListableBeanFactory child = new DefaultListableBeanFactory(parent);
		XmlBeanDefinitionReader definitionReader2 = new XmlBeanDefinitionReader(child);
		definitionReader2.loadBeanDefinitions(new ClassPathResource("applicationContext-child.xml"));

		// 建立联系，父子容器的信息会容器
		Stu stu = child.getBean(Stu.class);
		log.debug("[{}]", stu);
	}

	/**
	 * 抽象 bean
	 * 基本不使用
	 */
	@Test
	public void demo09() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		ClassPathResource resource = new ClassPathResource("applicationContext07.xml");
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		beanDefinitionReader.loadBeanDefinitions(resource);
		Stu stu = (Stu) beanFactory.getBean("ry");
		beanFactory.getBean("ry");
		log.debug("[{}]", stu);
	}

	/**
	 * 基于注解属性注入
	 */
	@Test
	public void demo10() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ProductConfig.class);
		Product product = (Product) applicationContext.getBean("product");
		log.debug("[{}]", product);
	}

	/**
	 * Autowird 注解使用
	 * 基于 AutowiredAnnotationBeanPostProcessor 完成
	 */
	@Test
	public void demo11() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ProductConfig.class);
		Product product = (Product) applicationContext.getBean("product");
		log.debug("[{}]", product);
	}

	/**
	 * 引用类型注入
	 */
	@Test
	public void demo12() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		beanDefinitionReader.loadBeanDefinitions(new ClassPathResource("applicationContext08.xml"));
		Item item = (Item) beanFactory.getBean("item");
		log.debug("[{}]", item);
	}

	/**
	 * aop
	 */
	@Test
	public void demo13() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext09.xml");
		ProductService productService = (ProductService) ctx.getBean("productService");
		productService.sell("phone");
	}


}
