package com.dbexercise.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LocalConnectionMakerDeprecated implements ConnectionMakerDeprecated {
    @Override
    public Connection makeConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc://mysql://localhost:3306/likelion-db", "root", "12345678");
        return conn;
    }
}
