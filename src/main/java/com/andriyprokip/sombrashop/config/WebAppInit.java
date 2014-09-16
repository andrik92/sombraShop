package com.andriyprokip.sombrashop.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Web application ininitialization class (instead of web.xml)
 * 
 */
public class WebAppInit extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * Creates root application context
	 */
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		return super.createRootApplicationContext();
	}

	/**
	 * Creates serlvet application context
	 */
	@Override
	protected WebApplicationContext createServletApplicationContext() {
		return super.createServletApplicationContext();
	}

	/**
	 * Application context config class
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class};
	}

	/**
	 * Dispatcher-servlet config class
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { DispatcherConfig.class };
	}

	/**
	 * Adds mapping
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/", "*.html", "*.htm" };
	}

	/**
	 * Specifies name of dispatcher-servlet
	 */
	@Override
	protected String getServletName() {
		return "dispatcher";
	}
}
