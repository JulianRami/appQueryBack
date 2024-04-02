package com.appQueries.versionOne.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        // Configure and return a DataSource for MySQL database
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // Set the driver class name for MySQL
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // Set the JDBC URL for the MySQL database
        dataSource.setUrl("jdbc:mysql://localhost:3306/appqueries");

        // Set the username for database authentication
        dataSource.setUsername("julian");

        // Set the password for database authentication
        dataSource.setPassword("123");

        return dataSource;
    }
}

