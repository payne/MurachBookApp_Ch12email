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
        this.dbUrl = "jdbc:mysql://localhost:3306/murach";
        this.userName = "root";
        this.pw = "BeNice";
        this.c = getConnection();
    }

    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection() {
        try {
            if (c == null || !c.isValid(10)) {
                System.out.format("Getting connection:%s\n%s\n%s\n",dbUrl,userName,pw);
                c = DriverManager.getConnection(dbUrl, userName, pw);
            }
        } catch (SQLException e) {
            c = null;
            e.printStackTrace();
        }
        return c;
    }

    public void freeConnection(Connection c) {
        System.out.println("refusing to close c=" + c);
    }
}