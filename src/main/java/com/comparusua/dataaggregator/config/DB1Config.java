package com.comparusua.dataaggregator.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.comparusua.dataaggregator.database.db1.repository",
        entityManagerFactoryRef = "db1EntityManager",
        transactionManagerRef = "db1TransactionManager"
)
public class DB1Config {

    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource db1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean db1EntityManager(EntityManagerFactoryBuilder builder, @Qualifier("db1DataSource") DataSource dataSource) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        return builder
                .dataSource(dataSource)
                .properties(properties)
                .packages("com.comparusua.dataaggregator.database.db1.entity")
                .persistenceUnit("data-base-1")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager db1TransactionManager(@Qualifier("db1EntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
