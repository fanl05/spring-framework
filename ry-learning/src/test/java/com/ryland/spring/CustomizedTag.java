package com.ryland.spring;

import com.ryland.spring.entity.Stu;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自定义标签
 * 1. xsd 文件定义标签规则
 * 2. spring.schemas 定义 schema 与 xsd 的映射关系
 * 3. 编写 handler 类继承 NamespaceHandlerSupport
 * 4.1 编写 Parser 类继承 AbstractSingleBeanDefinitionParser 将标签封装为 BeanDefinition（必须包含 id 属性）
 * 4.2 编写 Parser 类继承 BeanDefinitionParser 将标签封装为 BeanDefinition
 * 5. spring.handlers 定义 schema 与 handler 的映射关系
 * 6. applicationContext.xml 定义标签
 * 7. 从工厂中获取自定义标签中定义的对象
 *
 * NOTE: 自定义标签创建的对象必须包含 id 属性
 *
 * @author Ryland
 */
@Slf4j
public class CustomizedTag {

	@Test
	public void demo01() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext02.xml");
		Stu stu = applicationContext.getBean(Stu.class);
		log.debug("[{}]", stu);
	}

}
