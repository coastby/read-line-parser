package com.dbexercise.dao;


import com.dbexercise.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private JdbcTemplate jdbcTemplate;

    //JdbcContext를 JdbcTemplate으로 완전히 대체
    //JdbcTemplate DataSource를 이용해 초기화
    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //Jdbc Template 적용
    public void add(final User user){
        this.jdbcTemplate.update("INSERT INTO Users(id, name, password) VALUES(?, ?, ?);",
                user.getId(), user.getName(), user.getPassword());
    }
    //Jdbc Template 적용
    public void deleteAll(){
        this.jdbcTemplate.update("DELETE FROM Users;");
    }

    //RowMapper 이용
    public User findById(String id){
        String sql = "SELECT id, name, password FROM Users WHERE id=?";
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
                return user;
            }
        };
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<User> findAll(){
        String sql = "SELECT * FROM Users;";
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User(rs.getString("id"), rs.getNString("name"), rs.getString("password"));
                return user;
            }
        };
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public void deleteById(String id){
        this.jdbcTemplate.update("DELETE FROM Users WHERE id=?;", id);
    }


    public int getCount(){
        return this.jdbcTemplate.queryForObject("SELECT COUNT(*) AS COUNT FROM Users", Integer.class);
    }
}
