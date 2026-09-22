package vn.edu.democart.repository.impl;

import vn.edu.democart.model.Product;
import vn.edu.democart.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/22/2026
 */
public class ProductRepositoryImpl implements vn.edu.democart.repository.ProductRepository {
    private final DBUtil dbUtil;

    public ProductRepositoryImpl(DataSource dataSource) {

        this.dbUtil = new DBUtil(dataSource);
    }

    // READ ALL
    @Override
    public List<Product> getAllProducts() {

        List<Product> list = new ArrayList<>();

        String sql =
                "SELECT ID, MODEL, DESCRIPTION, QUANTITY, PRICE, IMGURL " +
                        "FROM products";

        try (
                Connection conn = dbUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                list.add(mapProduct(rs));
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Cannot load products",
                    e
            );
        }

        return list;
    }

    // READ BY ID
    @Override
    public Product getProductById(int id) {

        String sql =
                "SELECT ID, MODEL, DESCRIPTION, QUANTITY, PRICE, IMGURL " +
                        "FROM products WHERE ID = ?";

        try (
                Connection conn = dbUtil.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return mapProduct(rs);
                }
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Cannot load product id=" + id,
                    e
            );
        }

        return null;
    }

    @Override
    public Product mapProduct(ResultSet rs)
            throws SQLException {

        return new Product(

                rs.getInt("ID"),

                rs.getString("MODEL"),

                rs.getString("DESCRIPTION"),

                rs.getInt("QUANTITY"),

                rs.getDouble("PRICE"),

                rs.getString("IMGURL")
        );
    }
}
