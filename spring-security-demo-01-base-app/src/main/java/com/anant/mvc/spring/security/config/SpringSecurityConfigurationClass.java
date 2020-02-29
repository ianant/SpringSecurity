package com.anant.mvc.spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurationClass extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//Add some user for authentication
		
		UserBuilder users=User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication().withUser(users.username("ianant").password("test123").roles("employee","admin"));
		auth.inMemoryAuthentication().withUser(users.username("iashu").password("test123").roles("employee"));
		auth.inMemoryAuthentication().withUser(users.username("imohit").password("test123").roles("employee","manager"));
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*
		 * http.authorizeRequests() .anyRequest().authenticated() .and(). formLogin().
		 * loginPage("/showMyLoginPage"). loginProcessingUrl("/authenticateTheUser").
		 * permitAll(). and(). logout(). permitAll();
		 */
		
		http.authorizeRequests().
		antMatchers("/").hasRole("employee").
		antMatchers("/leaders/**").hasRole("manager").
		antMatchers("/systems/**").hasRole("admin")
		.and()
		.formLogin()
		.loginPage("/showMyLoginPage")
		.loginProcessingUrl("/authenticateTheUser")
		.permitAll()
		.and()
		.logout()
		.permitAll().and().exceptionHandling().accessDeniedPage("/access-denied");
		
		
		
		
	}
	
	

}
