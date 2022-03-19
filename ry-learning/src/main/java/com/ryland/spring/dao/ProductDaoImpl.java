package com.ryland.spring.dao;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Ryland
 */
@Slf4j
public class ProductDaoImpl implements ProductDao {
	@Override
	public void stockReduceBy(String name) {
		log.debug("stockReduceBy...");
	}
}
