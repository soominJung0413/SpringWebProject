package me.soomin.config;


import java.io.InputStream;
import java.net.URL;

import javax.sql.DataSource;

import org.apache.ibatis.javassist.ClassPath;
import org.apache.ibatis.javassist.NotFoundException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import me.soomin.persistence.BoardMapper;
import me.soomin.service.BoardService;
import me.soomin.service.BoardServiceImpl;

@Configuration
@ComponentScan(basePackageClasses= {BoardServiceImpl.class,BoardMapper.class})
@MapperScan(basePackageClasses=BoardMapper.class)
public class RootConfig {
	
	@Value("${dbcp.driver}")
	private String driver;
	
	@Value("${dbcp.url}")
	private String url;
	
	@Value("${dbcp.user}")
	private String user;
	
	@Value("${dbcp.password}")
	private String password;
	
	@Value("${dbcp.min}")
	private int min;
	
	@Value("${dbcp.size}")
	private int size;
	
	@Value("${dbcp.timeout}")
	private int timeout;
	
	@Value("${dbcp.test}")
	private String testSql;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer configurer() {
	PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
	configurer.setFileEncoding("UTF-8");
	configurer.setLocation(new ClassPathResource("/properties/dbcp.properties"));
	return configurer;
	}
	
	@Bean
	public HikariDataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(driver);
		config.setJdbcUrl(url);
		config.setUsername(user);
		config.setPassword(password);
		config.setMinimumIdle(min);
		config.setMaximumPoolSize(min);
		config.setValidationTimeout(timeout);
		config.setConnectionTestQuery(testSql);
		
		HikariDataSource dataSource = new HikariDataSource(config);
		
		return  dataSource;
	}
	
	@Bean
	public SqlSessionFactoryBean sessionFactory(DataSource dataSource) {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setMapperLocations(new ClassPathResource[] {new ClassPathResource("/me/soomin/mappers/BoardMapper.xml")});
		return factoryBean;
	}
	@Bean
	public SqlSessionTemplate sessionTemplate(SqlSessionFactory sessionFactory) {
		return new SqlSessionTemplate(sessionFactory);
	}
}
