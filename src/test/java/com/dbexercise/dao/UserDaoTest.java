package com.dbexercise.dao;

import com.dbexercise.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {
    @Autowired
    ApplicationContext context;

    @Test
    void addAndSelect () throws SQLException {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        String id = "1";
        User user = new User(id, "jo", "coconut");
        userDao.add(user);

        User user1 = userDao.findById(id);
        Assertions.assertEquals("jo", user1.getName());
    }

    @Test
    void findAllTest() throws SQLException {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            System.out.println(user.getName());
        }

    }

    @Test
    void deleteByIdTest() throws SQLException {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        String id = "100";
        User user = new User(id, "jo", "coconut");
        userDao.add(user);

        userDao.deleteById(id);

        User user1 = userDao.findById(id);
        Assertions.assertNull(user1);
    }

    @Test
    void deleteAllandCountTest() throws SQLException {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        userDao.deleteAll();

        Assertions.assertEquals(0, userDao.getCount());
    }

    //Assertion은 어떻게 할 지 생각해봐야겠음
    @Test
    void getCountTest() throws SQLException {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        System.out.println(userDao.getCount());
    }

}