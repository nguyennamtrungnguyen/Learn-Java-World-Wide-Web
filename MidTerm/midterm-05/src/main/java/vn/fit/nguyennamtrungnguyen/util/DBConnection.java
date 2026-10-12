package vn.fit.www_java_midterm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Noi dung 1b - Lop ket noi MariaDB bang JDBC.
 * Doi thong so bang bien moi truong DB_URL, DB_USER, DB_PASSWORD
 * hoac sua gia tri mac dinh ben duoi cho khop may thi.
 */
public final class DBConnection {

    private static final String URL      = env("DB_URL", "jdbc:mariadb://localhost:3306/companyDB");
    private static final String USER     = env("DB_USER", "root");
    private static final String PASSWORD = env("DB_PASSWORD", "sapassword");

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("Khong tim thay MariaDB JDBC driver: " + e.getMessage());
        }
    }

    private DBConnection() {
    }

    private static String env(String key, String defaultValue) {
        String v = System.getenv(key);
        return (v == null || v.isBlank()) ? defaultValue : v;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /** Chay main de kiem tra ket noi MariaDB. */
    public static void main(String[] args) {
        try (Connection c = getConnection()) {
            System.out.println("Ket noi MariaDB THANH CONG: " + c.getMetaData().getURL());
        } catch (SQLException e) {
            System.out.println("Ket noi THAT BAI: " + e.getMessage());
        }
    }
}
