package com.line.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class ConnectionMaker {
    public Connection makeConnection () throws SQLException, ClassNotFoundException {
        //environment variable에서 정보를 가져온다
        //소스코드에 직접적인 정보를 안 넣을 수 있다.
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");

        //db와 연결하는 단계
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword); //db연결

        return conn;
    }

    public static void main(String[] args) {
        System.out.println("hi");
    }
}
