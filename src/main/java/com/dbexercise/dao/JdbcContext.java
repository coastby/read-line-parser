package com.dbexercise.dao;

import javax.sql.DataSource;
import java.sql.*;

//다른 DAO에서도 사용 가능하니 클래스로 분리시키면, 재사용할 수 있다.
public class JdbcContext {
    private DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = dataSource.getConnection();

            ps = stmt.makePreparedStatement(conn);
            ps.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException(e);
        } catch (SQLException e) {
            throw e;
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
}
