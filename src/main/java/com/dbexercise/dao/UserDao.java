package com.dbexercise.dao;


import com.dbexercise.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    //DataSource를 의존한다.
    private DataSource dataSource;
    private JdbcContext jdbcContext;
    //생성자에 DataSource 초가화

    //JdbcContext도 DataSource를 이용해 초기화
    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcContext = new JdbcContext(dataSource);
    }

    //내부 클래스를 적용하여 여기서만 사용하는 클래스는 메소드 안에 정의한다.
    public void add(final User user) throws SQLException {
        jdbcContext.workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement("INSERT INTO Users(id, name, password) VALUES(?, ?, ?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());

                return ps;
            }
        });
    }
    public void deleteAll() throws SQLException {
        this.jdbcContext.executeSql("DELETE FROM Users;");
    }

    public User findById(String id){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement("SELECT id, name, password FROM Users WHERE id=?");
            ps.setString(1, id);

            rs = ps.executeQuery();
            User user = null;

            if (rs.next()){         //찾는 아이디가 있으면 해당 객체 반환, 없으면 null 반환
                user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
            }

            //user가 null이면 예외발생시킴
            if (user == null) {throw new EmptyResultDataAccessException(1);}
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }
    public List<User> findAll(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            //쿼리를 작성하는 코드
            ps = conn.prepareStatement("SELECT * FROM Users");
            rs = ps.executeQuery();
            List<User> userList = new ArrayList<>();
            while(rs.next()){
                User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public void deleteById(String id){
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();

            ps = conn.prepareStatement("DELETE FROM Users WHERE id=?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }


    public int getCount(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(*) AS COUNT FROM Users");
            rs = ps.executeQuery();
            rs.next();
            int cnt = rs.getInt("COUNT");
            return cnt;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
