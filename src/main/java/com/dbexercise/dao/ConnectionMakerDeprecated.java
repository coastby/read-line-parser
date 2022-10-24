package com.dbexercise.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMakerDeprecated {
    Connection makeConnection() throws SQLException;

}
