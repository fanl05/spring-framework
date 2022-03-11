package com.ryland.spring.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Ryland
 */
@Component
@Data
public class Category {

	@Value("3")
	private Integer level;

	@Value("三级品类")
	private String name;

}
