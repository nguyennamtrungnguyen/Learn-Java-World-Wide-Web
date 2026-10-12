package vn.fit.nguyennamtrungnguyen.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public class DBConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/CompanyDB";
    private static final String USER = "root";
    private static final String PASSWORD = "sapassword";

    public DBConnection(){}

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            throw  new RuntimeException("Khong tim thay database", e);
        }
        return DriverManager.getConnection(URL, USER,PASSWORD);
    }
}
