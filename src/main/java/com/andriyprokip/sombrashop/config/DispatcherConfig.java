package com.andriyprokip.sombrashop.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.SpringBeanPreparerFactory;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

/**
 * Dispatcher-servlet configurations
 * 
 */
@Configuration
@EnableWebMvc
@ComponentScan({ "com.andriyprokip.sombrashop.controller",
		"com.andriyprokip.sombrashop.rest" })
public class DispatcherConfig extends WebMvcConfigurerAdapter {

	/**
	 * Apache Tiles configurations
	 * 
	 * @return
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer
				.setDefinitions(new String[] { "/WEB-INF/defs/general.xml" });
		tilesConfigurer
				.setPreparerFactoryClass(SpringBeanPreparerFactory.class);
		return tilesConfigurer;
	}

	/**
	 * View resolver based on TilesView
	 * 
	 * @return UrlBasedViewResolver
	 */
	@Bean
	public UrlBasedViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		viewResolver.setOrder(0);
		return viewResolver;
	}

	/**
	 * Stores registrations of resource handlers for servingg static resources
	 * such as images, css files, etc.
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages/messages");
		messageSource.setFallbackToSystemLocale(true);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		return localeChangeInterceptor;
	}

	@Bean(name = "localeResolver")
	public SessionLocaleResolver sessionLocaleResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("en"));
		return localeResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

}
