package murach.data;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectionPool {

    private static ConnectionPool pool = null;
    private static Connection c = null;
    private String dbUrl, userName, pw;

    private ConnectionPool() {
this.dbUrl=                "jdbc:mysql://localhost:3306/murach";
this.userName=                "root";
this.pw=                "BeNice";
        try {
            this.c = DriverManager.getConnection(dbUrl, userName, pw);
        } catch (SQLException e) {
            c=null;
            e.printStackTrace();
        }
    }
/*
    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/murach");
        } catch (NamingException e) {
            System.out.println(e);
        }
    }
 */

    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection() {
        //TODO: When connection goes bad -- how to reopen?
            System.out.println("returning c="+c);
            return c;
    }

    public void freeConnection(Connection c) {
        System.out.println("refusing to close c="+c);
    }
}