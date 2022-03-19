package com.ryland.spring.circuit.reference;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Ryland
 */
@Component
@Slf4j
public class A {

	@Autowired
	@Getter
	@Setter
	private B b;

	public void show() {
		log.debug("A show...");
	}

}
