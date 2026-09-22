package vn.edu.democart.util;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/22/2026
 */
public class DBUtil {
    private final DataSource dataSource;

    public DBUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {

        try {

            return dataSource.getConnection();

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Cannot get database connection",
                    e
            );
        }
    }
}
