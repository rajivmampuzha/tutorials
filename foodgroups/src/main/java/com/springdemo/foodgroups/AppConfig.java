package com.springdemo.foodgroups;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.springdemo.foodgroups.dao.FoodGroupDao;

@Configuration
@PropertySources({ @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true), })
public class AppConfig {
	@Value("${mysql.url}")
	String url;
	@Value("${mysql.userName}")
	String userName;

	@Value("${mysql.password}")
	String password;

	@Value("${mysql.minPoolSize}")
	int minPoolSize;
	@Value("${mysql.maxPoolSize}")
	int maxPoolSize;

	@Bean(initMethod = "migrate")
	Flyway flyway() {
		Flyway flyway = new Flyway();
		flyway.setBaselineOnMigrate(true);
		flyway.setLocations("classpath:/sqls/");
		flyway.setDataSource(drivermanagerDataSource());
		return flyway;
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		// dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		dataSource.setInitialSize(minPoolSize);
		dataSource.setMaxTotal(maxPoolSize);
		return dataSource;

	}
	
	@Bean
	public DataSource drivermanagerDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		// dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
	
		return dataSource;

	}
	@Bean
	public FoodGroupDao foodGroupDao() {
		FoodGroupDao dao = new FoodGroupDao();
		dao.setJdbcTemplate(dataSource());
		return dao;
	}
}
