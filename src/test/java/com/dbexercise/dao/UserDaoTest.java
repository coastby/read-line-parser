package com.dbexercise.dao;

import com.dbexercise.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    //    UserDao userDao = new UserDao(new AwsConnectionMaker());
    UserDao userDao = new UserDaoFactory().awsUserDao();
    @Test
    void addAndSelect () throws SQLException {
        String id = "5";
        User user = new User(id, "jo", "coconut");
        userDao.add(user);

        User user1 = userDao.findById(id);
        Assertions.assertEquals("jo", user1.getName());
    }

    @Test
    void findAllTest() throws SQLException {
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            System.out.println(user.getName());
        }

    }

    @Test
    void deleteByIdTest() throws SQLException {
        String id = "100";
        User user = new User(id, "jo", "coconut");
        userDao.add(user);

        userDao.deleteById(id);

        User user1 = userDao.findById(id);
        Assertions.assertNull(user1);
    }

    @Test
    void deleteAllandCountTest() throws SQLException {
        userDao.deleteAll();

        Assertions.assertEquals(0, userDao.getCount());
    }

    //Assertion은 어떻게 할 지 생각해봐야겠음
    @Test
    void getCountTest() throws SQLException {
        System.out.println(userDao.getCount());
    }

}