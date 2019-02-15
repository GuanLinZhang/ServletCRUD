package main.java.Dao;

import main.java.Bean.EmpBean;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpDao {
    private static Connection conn;
    private static DataSource ds;
    private static ServletContext ctx;

    public EmpDao(ServletContext newCtx) {
        if (ctx == null) {
            ctx = newCtx;
        }
        if (ds == null) {
            ds = (DataSource) ctx.getAttribute("dataSource");
        }
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

    public static void close(Connection conn, Statement stmt,ResultSet set) {
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

    public int save(EmpBean e) {
        int status = 0;
        PreparedStatement pstmt = null;

        try {
            pstmt = getConn().prepareStatement("insert into emp values (?,?,?,?,?)");
            pstmt.setInt(1, e.getId());
            pstmt.setString(2, e.getName());
            pstmt.setString(3, e.getPassword());
            pstmt.setString(4, e.getEmail());
            pstmt.setString(5, e.getCountry());

            pstmt.executeUpdate();
            status = 200;
        } catch (SQLException e1) {
            e1.printStackTrace();
            status = 400;
        } finally {
            close(conn,pstmt);
        }
        return status;
    }

    public int update(EmpBean e) {
        int status = 0;
        PreparedStatement pstmt = null;
        try {
            pstmt = getConn().prepareStatement(
                        "update emp " +
                            "set NAME = ?,PASSWORD = ?,EMAIL = ?,COUNTRY = ? " +
                            "where ID = ?");
            pstmt.setString(1, e.getName());
            pstmt.setString(2, e.getPassword());
            pstmt.setString(3, e.getEmail());
            pstmt.setString(4, e.getCountry());
            pstmt.setInt(5, e.getId());

            pstmt.executeUpdate();
            status = 200;
        } catch (SQLException e1) {
            e1.printStackTrace();
            status = 400;
        } finally {
            close(conn,pstmt);
        }
        return status;
    }

    public int delete(int id) {
        int status = 0;
        PreparedStatement pstmt = null;
        try {
            pstmt = getConn().prepareStatement("delete from emp where ID = ?");

            pstmt.setInt(1, id);
            pstmt.execute();
            status = 200;
        } catch (SQLException e) {
            e.printStackTrace();
            status = 400;
        } finally {
            close(conn,pstmt);
        }
        return status;
    }

    public EmpBean getEmployeeByID(int id) {
        PreparedStatement pstmt = null;
        ResultSet result = null;

        try {
            pstmt = getConn().prepareStatement("select * from emp where ID = ?");

            pstmt.setInt(1, id);
            result = pstmt.executeQuery();

            if (result.next()) {
                EmpBean e = new EmpBean();
                e.setId(id);
                e.setName(result.getString(2));
                e.setPassword(result.getString(3));
                e.setEmail(result.getString(4));
                e.setCountry(result.getString(5));

                return e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn,pstmt,result);
        }

        return null;
    }

    public List<EmpBean> getAllEmployees() {
        PreparedStatement pstmt = null;
        ResultSet result = null;
        try {
            pstmt = getConn().prepareStatement("select * from emp;");
            result = pstmt.executeQuery();

            List<EmpBean> beanList = new ArrayList<>();
            while (result.next()) {
                EmpBean e = new EmpBean();
                e.setId(result.getInt(1));
                e.setName(result.getString(2));
                e.setPassword(result.getString(3));
                e.setEmail(result.getString(4));
                e.setCountry(result.getString(5));

                beanList.add(e);
            }
            return beanList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn,pstmt,result);
        }
        return null;
    }



}
