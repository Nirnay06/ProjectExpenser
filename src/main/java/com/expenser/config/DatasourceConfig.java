package com.expenser.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@Configuration
public class DatasourceConfig {
	@Value("${spring.datasource.driver-class-name}")
	private String dataSourceDriver;
	
	@Value("${spring.datasource.url}")
	private String dataSourceUrl;
	
	@Value("${spring.datasource.username}")
	private String datasourceUsername;
	
	@Value("${spring.datasource.password}")
	private String dataSourcePassword;
	
	 @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(dataSourceDriver);
	        dataSource.setUrl(dataSourceUrl);
	        dataSource.setUsername(datasourceUsername);
	        dataSource.setPassword(dataSourcePassword);
	 
	        return dataSource;
	    }
	 
	
}
