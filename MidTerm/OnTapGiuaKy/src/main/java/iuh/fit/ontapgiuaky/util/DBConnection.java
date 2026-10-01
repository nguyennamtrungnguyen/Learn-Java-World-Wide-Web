package iuh.fit.ontapgiuaky.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/30/2026
 */
public class DBConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/storedb";
    private static final String USER = "root";
    private static final String PASSWORD = "sapassword";

    private DBConnection(){}
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            throw new RuntimeException("Khong tim thay MariaDB JDBC Driver", e);

        }
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
