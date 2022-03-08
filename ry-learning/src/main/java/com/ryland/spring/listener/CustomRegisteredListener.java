package com.ryland.spring.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.ComponentDefinition;
import org.springframework.beans.factory.parsing.EmptyReaderEventListener;

import java.util.Arrays;

/**
 * @author Ryland
 */
@Slf4j
public class CustomRegisteredListener extends EmptyReaderEventListener {

	/**
	 * 注册完成 BeanDefinition 后的回调
	 */
	@Override
	public void componentRegistered(ComponentDefinition componentDefinition) {
		Arrays.stream(componentDefinition.getBeanDefinitions())
				.filter(BeanDefinition::hasPropertyValues)
				.forEach(bd -> log.debug("[{}]", bd));
	}
}
