package vn.edu.democart.repository;

import vn.edu.democart.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/22/2026
 */
public interface ProductRepository {
    // READ ALL
    List<Product> getAllProducts();

    // READ BY ID
    Product getProductById(int id);

    Product mapProduct(ResultSet rs)
            throws SQLException;
}
