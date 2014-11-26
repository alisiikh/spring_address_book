package org.addressbook.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author alisiikh
 */
@Configuration
@EnableJpaRepositories(basePackages = "org.addressbook.persistence.dao")
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaAuditing(setDates = true)
@PropertySource(value = "classpath:hibernate.properties")
public class DataSourceConfig {

    public static final String HIBERNATE_DIALECT_PROPERTY = "hibernate.dialect";
    public static final String HIBERNATE_HBM2DDL_PROPERTY= "hibernate.hbm2ddl.auto";
    public static final String HIBERNATE_FORMAT_SQL_PROPERTY = "hibernate.format_sql";
    public static final String HIBERNATE_SHOW_SQL_PROPERTY = "hibernate.show_sql";
    public static final String HIBERNATE_NAMING_STRATEGY_PROPERTY = "hibernate.ejb.naming_strategy";

    @Resource
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getProperty("jdbc.databaseUrl"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));

        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        localContainerEntityManagerFactoryBean.setPackagesToScan("org.addressbook.persistence.domain");

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty(HIBERNATE_DIALECT_PROPERTY, environment.getProperty(HIBERNATE_DIALECT_PROPERTY));
        jpaProperties.setProperty(HIBERNATE_HBM2DDL_PROPERTY, environment.getProperty(HIBERNATE_HBM2DDL_PROPERTY));
        jpaProperties.setProperty(HIBERNATE_FORMAT_SQL_PROPERTY, environment.getProperty(HIBERNATE_FORMAT_SQL_PROPERTY));
        jpaProperties.setProperty(HIBERNATE_SHOW_SQL_PROPERTY, environment.getProperty(HIBERNATE_SHOW_SQL_PROPERTY));
        jpaProperties.setProperty(HIBERNATE_NAMING_STRATEGY_PROPERTY, environment.getProperty(HIBERNATE_NAMING_STRATEGY_PROPERTY));

        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);

        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }
}
