package org.mycode.configs;

import liquibase.integration.spring.SpringLiquibase;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan({"org.mycode.assembler"})
@PropertySource(value = "classpath:/config.properties")
@EnableJpaRepositories(basePackages = "org.mycode.repository")
public class DatabaseConfig {
    @Autowired
    private Environment environment;
    @Autowired
    private SetupConfig setupConfig;

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog.xml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(setupConfig.getProperty("jdbc.driver"));
        dataSource.setUrl(setupConfig.getProperty("jdbc.url"));
        dataSource.setUsername(setupConfig.getProperty("jdbc.user"));
        dataSource.setPassword(setupConfig.getProperty("jdbc.password"));
        dataSource.setConnectionProperties("serverTimezone=UTC");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("org.mycode.model");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());
        return em;
    }

    private Properties hibernateProperties() {
        String[] needProperties = {
                "hibernate.hbm2ddl.auto",
                "hibernate.show_sql",
                "hibernate.format_sql",
                "hibernate.dialect"
        };
        Properties properties = new Properties();
        for (String prop : needProperties) {
            properties.setProperty(prop, environment.getProperty(prop));
        }
        return properties;
    }
}
