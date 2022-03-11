package com.ryland.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Ryland
 */
@Configuration
@ComponentScan(basePackages = "com.ryland.spring.entity")
@PropertySource("classpath:app.properties")
public class ProductConfig {
}
