package com.dbexercise.dao;

import com.dbexercise.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {
    @Autowired
    ApplicationContext context;
    private UserDao userDao;
    private User user1;
    private User user2;
    private User user3;


    @BeforeEach
    void setContext() throws SQLException {
        userDao = context.getBean("awsUserDao", UserDao.class);
        user1 = new User("1", "hoon", "hungry");
        user2 = new User("2", "tobby", "spring");
        user3 = new User("3", "dd", "work");

    }

    @Test
    void addAndFindByIdTest() throws SQLException {
        //add() test
        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);
        //findById() id로 가져온 정보가 맞는 지 확인
        User userT = userDao.findById(user1.getId());
        assertEquals(user1.getName(), userT.getName());
        assertEquals(user1.getPassword(), userT.getPassword());

        //아이디가 중복될 때 예외처리되는지 확인
        assertThrows(SQLIntegrityConstraintViolationException.class, () ->{
            userDao.add(user1);
        });

        //없는 id를 찾을 때 예외처리되는지 확인
        assertThrows(EmptyResultDataAccessException.class, () -> {
            User user = userDao.findById("4");
        });

    }

    //미완성
    @Test
    void findAllTest() throws SQLException {
        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);

        List<User> userList = userDao.findAll();
//        for (User user : userList) {
//            assertEquals(name, user.getName());
//        }

    }

    @Test
    void deleteByIdTest() throws SQLException {
        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);

        userDao.deleteById(user1.getId());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            User userT = userDao.findById(user1.getId());
        });
    }
    @Test
    void getCountTest() throws SQLException {
        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);

        assertEquals(3, userDao.getCount());

        userDao.deleteById("1");
        assertEquals(2, userDao.getCount());
    }

    @Test
    void deleteAllandCountTest(){
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());
    }

    @AfterEach
    void after(){
        userDao.deleteAll();
    }
}