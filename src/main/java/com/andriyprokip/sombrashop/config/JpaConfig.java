package com.andriyprokip.sombrashop.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Jpa configurations
 *  
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.andriyprokip.sombrashop.dao")
@PropertySource({ "classpath:project.properties" })
public class JpaConfig {

	/**
	 * Represents environment where app is running (needed to get properties
	 * from properties source)
	 */
	@Autowired
	Environment env;

	/**
	 * Bean that defines out EntityManagerFactory properties:datasource - db
	 * configs; packagesToScan - package with entities; jpaVendorAdapter -
	 * vendor of jpa (Hibernate); jpaProperties - hibernate properties;
	 * 
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setPersistenceUnitName("emf");
		emf.setDataSource(dataSource());
		emf.setPackagesToScan(new String[] { "com.andriyprokip.sombrashop.entity" });
		final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(vendorAdapter);
		emf.setJpaPropertyMap(jpaProperties());
		return emf;
	}

	/**
	 * Bean that defines datasoure. Datasource is property of
	 * entityManagerFactoryBean that defines db configs.
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));
		return dataSource;
	}

	/**
	 * Bean post-processor that automatically applies persistence exception
	 * translation to any bean that carries the Repository annotation
	 * 
	 * @return
	 */
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	/**
	 * Bean that defines transaction manager that manages transactions.
	 * 
	 * @param emf
	 * @return
	 */
	@Bean
	public PlatformTransactionManager transactionManager(
			final EntityManagerFactory emf) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager(
				emf);
		return transactionManager;
	}
	

	/**
	 * Additional properties from project.properties files.
	 * 
	 * @return
	 */
	Map<String, String> jpaProperties() {
		Map<String, String> jpaProperties = new HashMap<>();
		jpaProperties.put("hibernate.hbm2ddl.auto",
						env.getProperty("hibernate.hbm2ddl.auto"));
		jpaProperties.put("hibernate.dialect", 
						env.getProperty("hibernate.dialect"));
		jpaProperties.put("hibernate.show_sql", 
						env.getProperty("hibernate.show_sql"));
		return jpaProperties;
	}

}
