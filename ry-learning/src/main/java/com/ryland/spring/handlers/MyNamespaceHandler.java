package com.ryland.spring.handlers;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author Ryland
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("stu", new StuBeanDefinitionParser());
	}

}
