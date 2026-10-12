package vn.fit.giaide01.util;

import org.mariadb.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/CompanyDB";
    private static final String USER = "root";
    private static final String PASSWORD = "sapassword";

    private DBConnection(){}
    public static Connection getConnection() throws SQLException {
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
         catch (ClassNotFoundException e) {
            throw new RuntimeException("Khong tim thay mariadb",e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
