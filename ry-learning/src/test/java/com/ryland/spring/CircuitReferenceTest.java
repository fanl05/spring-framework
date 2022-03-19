package com.ryland.spring;

import com.ryland.spring.circuit.reference.A;
import com.ryland.spring.circuit.reference.B;
import com.ryland.spring.circuit.reference.MyConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Ryland
 */
@Slf4j
public class CircuitReferenceTest {

	/**
	 * 循环引用测试
	 */
	@Test
	public void demo01() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-circuit-reference01.xml");
		A a = (A) ctx.getBean("a");
		B b = (B) ctx.getBean("b");
		a.show();
		b.show();
	}

	/**
	 * 重写 SmartInstantiationAwareBeanPostProcessor 中的 getEarlyBeanReference
	 * throw NPE
	 */
	@Test
	public void demo02() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-circuit-reference02.xml");
		A a = (A) ctx.getBean("a");
		a.show();
	}

	/**
	 * 注解开发
	 */
	@Test
	public void demo03() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);
		A a = (A) ctx.getBean("a");
		a.show();
		log.debug("[{}]", a.getB());
	}

}
