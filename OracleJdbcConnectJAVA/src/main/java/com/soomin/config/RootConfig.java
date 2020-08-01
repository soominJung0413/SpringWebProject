package com.soomin.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"com.soomin.sample"})
public class RootConfig {
	
	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
		hikariConfig.setUsername("book_ex");
		hikariConfig.setPassword("1234");
		hikariConfig.setIdleTimeout(6000*3);
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setMinimumIdle(2);
		
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}
}
