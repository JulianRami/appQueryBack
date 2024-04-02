package com.appQueries.versionOne.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    //@Value("${DB_USERNAME}")
    //private String username;

    //@Value("${DB_PASSWORD}")
    //private String password;

    //@Value("${DB_URL}")
    //private String urlBD;

    @Bean
    public DataSource dataSource() {
        // Configure and return a DataSource for MySQL database
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // Set the driver class name for MySQL
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // Set the JDBC URL for the MySQL database
        dataSource.setUrl("jdbc:mysql://localhost:3306/appqueries");
        //dataSource.setUrl(urlBD);

        // Set the username for database authentication
        dataSource.setUsername("julian");
        //dataSource.setUsername(username);

        // Set the password for database authentication
        dataSource.setPassword("123");
        //dataSource.setPassword(password);

        return dataSource;
    }
}

