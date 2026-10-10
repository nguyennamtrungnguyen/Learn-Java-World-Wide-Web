package vn.fit.nguyennamtrungnguyen.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/10/2026
 */
public class DBConnection {
    private static final String URL ="jdbc:mariadb://localhost:3306/quanlytintuc";
    private static final String USER = "root";
    private static final String PASSWORD = "sapassword";

    private DBConnection(){}
    public static Connection getConnection()throws SQLException{
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            throw new RuntimeException("Không tìm thất Database", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
