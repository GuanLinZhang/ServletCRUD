package main.java.Dao;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseDao {
    private static Connection conn;
    private static DataSource ds;
    private static ServletContext ctx;

    public BaseDao(ServletContext newCtx) {

    }

    public static Connection getConn() {
        try {
            conn = ds.getConnection();
            System.out.println("established a new conn");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn, Statement stmt, ResultSet set) {
        try {
            if (set != null) {
                set.close();
                set = null;
            }
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
                System.out.println("conn closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection conn, Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
                System.out.println("conn closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
