package com.dbexercise.dao;

public class UserDaoFactory {
    //조립을 해주는 역할을 한다.
    //직접 생성자를 이용하여 객체를 생성하는 대신 메소드를 통해 객체를 생성하는 것을 원칙으로 한다.
    public UserDao awsUserDao(){
        AwsConnectionMaker acm = new AwsConnectionMaker();
        UserDao userDao = new UserDao(acm);
        return userDao;
    }

    public UserDao localUserDao() {
        UserDao userDao = new UserDao(new LocalConnectionMaker());
        return userDao;
    }
}
