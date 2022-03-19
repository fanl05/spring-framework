package com.ryland.spring.ctx.import_;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Ryland
 */
@Configuration
@Import(MyImportSelector.class)
public class AppConfig3 {
}
