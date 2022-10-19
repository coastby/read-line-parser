package com.dbexercise.dao;


import com.dbexercise.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public User findById(String id) throws SQLException {
        Connection conn = connectionMaker.makeConnection();
        //쿼리를 작성하는 코드
        PreparedStatement ps = conn.prepareStatement("SELECT id, name, password FROM Users WHERE id=?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        User user;

        if (rs.next()){         //찾는 아이디가 있으면 해당 객체 반환, 없으면 null 반환
            user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
        } else {
            System.out.println("찾는 ID가 없습니다.");
            return null;
        }

        System.out.println("SELECT 완료");
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
        System.out.println("SELECT 완료");
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

        System.out.println(id +" 삭제 완료");
        ps.close();
        conn.close();
    }

    public void deleteAll() throws SQLException {
        Connection conn = connectionMaker.makeConnection();
        //쿼리를 작성하는 코드
        PreparedStatement ps = conn.prepareStatement("TRUNCATE TABLE Users");
        ps.executeUpdate();

        System.out.println("테이블 비우기 완료");
        ps.close();
        conn.close();
    }

    public int getCount() throws SQLException {
        Connection conn = connectionMaker.makeConnection();
        //쿼리를 작성하는 코드
        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) AS COUNT FROM Users");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int cnt = rs.getInt("COUNT");
        rs.close();
        ps.close();
        conn.close();

        return cnt;
    }


}
