package me.soomin.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import me.soomin.mappers.BoardMapper;

@Configuration
@MapperScan(basePackageClasses = { BoardMapper.class })
public class RootConfig {
	@Value("${dbcp.driver}")
	private String driverClassName;
	@Value("${dbcp.url}")
	private String jdbcUrl;
	@Value("${dbcp.user}")
	private String username;
	@Value("${dbcp.password}")
	private String password;
	@Value("${dbcp.min}")
	private int minIdle;
	@Value("${dbcp.size}")
	private int maxPoolSize;
	@Value("${dbcp.test}")
	private String connectionTestQuery;
	@Value("${dbcp.timeout}")
	private long idleTimeoutMs;

	@Bean
	public static PropertySourcesPlaceholderConfigurer configurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer.setFileEncoding("utf-8");
		configurer.setLocation(new ClassPathResource("/properties/dbcp.properties"));
		return configurer;
	}

	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(driverClassName);
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(username);
		config.setPassword(password);
		config.setMinimumIdle(minIdle);
		config.setMaximumPoolSize(maxPoolSize);
		config.setConnectionTestQuery(connectionTestQuery);
		config.setIdleTimeout(idleTimeoutMs);
		return config;
	}

	@Bean
	public HikariDataSource dataSource(HikariConfig config) {
		return new HikariDataSource(config);
	}

	@Bean
	public SqlSessionFactoryBean factoryBean(HikariDataSource dataSource) {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		return factoryBean;
	}

	@Bean
	public SqlSessionTemplate sessionTemplate(SqlSessionFactory factoryBean) {
		return new SqlSessionTemplate(factoryBean);
	}
}
