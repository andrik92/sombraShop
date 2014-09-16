package com.andriyprokip.sombrashop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

import com.andriyprokip.sombrashop.listener.AutowireHelper;

/**
 * Application context configuration
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.andriyprokip.sombrashop" }, excludeFilters = {
		@ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION),
		@ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION) })
@EnableAspectJAutoProxy
@Import({ JpaConfig.class, WebSecurityConfig.class, MultipartConfig.class })
public class AppConfig {

	/**
	 * AutowireHelper
	 * 
	 * @return
	 */
	@Bean
	public AutowireHelper autowireHelper() {
		return new AutowireHelper();
	}

}
