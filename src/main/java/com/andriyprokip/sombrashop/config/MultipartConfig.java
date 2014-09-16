package com.andriyprokip.sombrashop.config;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class MultipartConfig {

	@Bean
	CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(4194304l);
		return multipartResolver;
	}

}
