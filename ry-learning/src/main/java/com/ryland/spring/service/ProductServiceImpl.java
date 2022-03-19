package com.ryland.spring.service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Ryland
 */
@Slf4j
public class ProductServiceImpl implements ProductService{
	@Override
	public void sell(String name) {
		log.debug("sell...");
	}
}
