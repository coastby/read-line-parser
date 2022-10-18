package com.line.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class UserDao {
    public void add() throws ClassNotFoundException, SQLException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO user (id, name, password) VALUES (?, ?, ?)");
        ps.setString(1, "1");
        ps.setString(2, "hoon");
        ps.setString(3, "7");

        ps.executeUpdate();
        ps.close();
        conn.close();

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao ud = new UserDao();
        ud.add();
    }
}
