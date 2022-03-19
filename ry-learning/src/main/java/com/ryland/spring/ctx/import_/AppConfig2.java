package com.ryland.spring.ctx.import_;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Ryland
 */
@Configuration
@Import(TestBean2.class)
public class AppConfig2 {
}
