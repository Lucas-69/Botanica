package ar.edu.um.ingenieria.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "ar.edu.um.ingenieria")
@PropertySource(value = { "classpath:application.properties" })
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ar.edu.um.ingenieria.repository")
public class AppConfig {

	@Autowired
	private Environment environment;

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(Boolean.TRUE);
		vendorAdapter.setShowSql(Boolean.valueOf(environment
				.getProperty("jpa.show-sql")));

		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("ar.edu.um.ingenieria");

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect",environment.getProperty("jpa.database-platform"));
		factory.setJpaProperties(jpaProperties);

		factory.afterPropertiesSet();
		factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		return factory;
	}

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("datasource.driver-class-name"));
		dataSource.setUrl(environment.getProperty("datasource.url"));
		dataSource.setUsername(environment.getProperty("datasource.username"));
		dataSource.setPassword(environment.getProperty("datasource.password"));
		return dataSource;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory()
				.getObject());
		return transactionManager;
	}
	
}