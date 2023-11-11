package com.hss.rinhabackend.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.hss.rinhabackend.repository")
public class DataSourceConfig {

    @Bean(name = "DatasourceRinha")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getMusicConnection() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        return dataSourceBuilder.type(HikariDataSource.class).build();
    }
}
