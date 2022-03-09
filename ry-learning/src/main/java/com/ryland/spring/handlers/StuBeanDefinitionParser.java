package com.ryland.spring.handlers;

import com.ryland.spring.entity.Stu;
import com.ryland.spring.entity.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author Ryland
 */
public class StuBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return Stu.class;
	}

	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		int id = Integer.parseInt(element.getAttribute("id"));
		String name = element.getAttribute("name");
		builder.addPropertyValue("id", id).addPropertyValue("name", name);
	}
}
