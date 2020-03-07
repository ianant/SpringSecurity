package com.anant.mvc.spring.security.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author Anant
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.anant.mvc.spring.security")
@PropertySource("classpath:persistence-mysql.properties")
public class ConfigDemo {
	
	@Autowired
	public Environment env;//this holds the data of properties file
	
	Logger logger=Logger.getLogger(ConfigDemo.class.getName());
	

	//define a bean for the view resolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	//define a bean for datasource 
	@Bean
	public DataSource dataSource()  {
		
		//create a connection pool
		ComboPooledDataSource securityDataSource=new ComboPooledDataSource();
		
		//log the connection properties
		
		logger.info(">>>>JDBC Driver"+env.getProperty("jdbc.driver"));
		logger.info(">>>>JDBC Url"+env.getProperty("jdbc.url"));
		logger.info(">>>>JDBC User"+env.getProperty("jdbc.user"));
		//logger.info(">>>>JDBC Driver"+env.getProperty("jdbc.driver"));
		
		
		
		//set the jdbc driver class
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			
			throw new RuntimeException(e);
		}	
		
		//set database connection properties
		
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		//set connection pool properties
		
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return securityDataSource;
	}
	
	/*
	 * You need to create helper method primarily for reading an environment
	 * property and converting it to an int because whenver you read a property it
	 * always comes as String,but there are some scenarios where you need to set the
	 * property as int and you have to do it with multiple times for multiple
	 * properties
	 */
	
	private int getIntProperty(String propName) {
		String propValue=env.getProperty(propName);
		int intPropvalue=Integer.parseInt(propValue);
		return intPropvalue;
	}
	
	
}
