package com.ryland.spring.circuit.reference;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Ryland
 */
@Component
@Slf4j
public class B {

	@Autowired
	@Getter
	@Setter
	private A a;

	public void show() {
		log.debug("B show...");
	}

}
