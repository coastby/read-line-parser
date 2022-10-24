package com.dbexercise.dao;

import com.dbexercise.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class UserDaoFactory {
    //조립을 해주는 역할을 한다.
    //직접 생성자를 이용하여 객체를 생성하는 대신 메소드를 통해 객체를 생성하는 것을 원칙으로 한다.

    @Bean
    UserDao awsUserDao() {
        return new UserDao(awsDataSource());
    }
    @Bean
    UserDao localUserDao() {
        return new UserDao(localDataSource());
    }
    @Bean
    DataSource localDataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl("jdbc://mysql://localhost:3306/likelion-db");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");

        return dataSource;
    }
    @Bean
    DataSource awsDataSource() {
        Map<String, String> env = System.getenv();
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl(env.get("DB_HOST"));
        dataSource.setUsername(env.get("DB_USER"));
        dataSource.setPassword(env.get("DB_PASSWORD"));
        return dataSource;
    }
}
