package com.dbexercise.dao;


import com.dbexercise.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws SQLException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO Users(id, name, password) VALUES(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        //ctrl+enter
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLIntegrityConstraintViolationException(e);
        }
        ps.close();
        conn.close();
    }

    public User findById(String id) throws SQLException {
        Connection conn = connectionMaker.makeConnection();
        //쿼리를 작성하는 코드
        PreparedStatement ps = conn.prepareStatement("SELECT id, name, password FROM Users WHERE id=?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        User user = null;

        if (rs.next()){         //찾는 아이디가 있으면 해당 객체 반환, 없으면 null 반환
            user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
        }

        //user가 null이면 예외발생시킴
        if (user == null) {throw new EmptyResultDataAccessException(1);}

        rs.close();
        ps.close();
        conn.close();

        return user;
    }
    public List<User> findAll() throws SQLException {
        Connection conn = connectionMaker.makeConnection();
        //쿼리를 작성하는 코드
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users");
        ResultSet rs = ps.executeQuery();
        List<User> userList = new ArrayList<>();
        while(rs.next()){
            User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
            userList.add(user);
        }
        rs.close();
        ps.close();
        conn.close();

        return userList;
    }

    public void deleteById(String id) throws SQLException {
        Connection conn = connectionMaker.makeConnection();
        //쿼리를 작성하는 코드
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Users WHERE id=?");
        ps.setString(1, id);
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public void deleteAll() {
        Connection conn = null;
        try {
            conn = connectionMaker.makeConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Users");
            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //쿼리를 작성하는 코드
    }

    public int getCount(){
        Connection conn = null;
        try {
            conn = connectionMaker.makeConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) AS COUNT FROM Users");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int cnt = rs.getInt("COUNT");
            rs.close();
            ps.close();
            conn.close();
            return cnt;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //쿼리를 작성하는 코드

    }


}
