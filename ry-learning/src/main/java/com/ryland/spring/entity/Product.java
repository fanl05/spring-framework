package com.ryland.spring.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Ryland
 */
@Component
@Data
public class Product {

	@Value("${product.id}")
	private Integer id;

	@Value("${product.name}")
	private String name;

	@Autowired
	private Category category;

}
