package com.andriyprokip.sombrashop.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/editProfile").authenticated()
				.antMatchers("/cart/*").authenticated()
				.antMatchers("/admEditUsers/1**").hasRole("ADMIN")
				.antMatchers("/admEditCity").hasRole("ADMIN")
				.antMatchers("/admEditCategory/**").hasRole("ADMIN")
				.antMatchers("/user").authenticated()
				.antMatchers("/createLot/").authenticated()
				.antMatchers("*/uploadLotImage").authenticated()
				.and().formLogin()
					.loginPage("/login")
				.and()
				.logout();
				
	}

	@Autowired
    public void configureGlobal(DataSource dataSource, AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource).passwordEncoder(passwordEncoder())
		.usersByUsernameQuery("select login,password,enabled from user where login = ?")
		.authoritiesByUsernameQuery("select user.Login, role.name from user " +
				"join user_role on user.userId = user_role.users_userId join role on user_role.roles_id = role.id where user.login = ?");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
