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
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectionMaker.makeConnection();

            ps = conn.prepareStatement("INSERT INTO Users(id, name, password) VALUES(?, ?, ?)");
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException(e);
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

    public User findById(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connectionMaker.makeConnection();
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
            throw e;
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
    public List<User> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectionMaker.makeConnection();
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

    public void deleteById(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectionMaker.makeConnection();

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

    public void deleteAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = connectionMaker.makeConnection();
            ps = conn.prepareStatement("DELETE FROM Users");
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
            conn = connectionMaker.makeConnection();
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
