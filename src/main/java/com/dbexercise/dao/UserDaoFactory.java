package com.dbexercise.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoFactory {
    //조립을 해주는 역할을 한다.
    //직접 생성자를 이용하여 객체를 생성하는 대신 메소드를 통해 객체를 생성하는 것을 원칙으로 한다.
    @Bean
    public UserDao awsUserDao(){
        AwsConnectionMaker acm = new AwsConnectionMaker();
        UserDao userDao = new UserDao(acm);
        return userDao;
    }
    @Bean
    public UserDao localUserDao() {
        UserDao userDao = new UserDao(new LocalConnectionMaker());
        return userDao;
    }
}
