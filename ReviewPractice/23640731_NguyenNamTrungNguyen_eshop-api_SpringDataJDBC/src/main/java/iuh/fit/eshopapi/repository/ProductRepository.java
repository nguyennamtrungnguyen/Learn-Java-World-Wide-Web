package iuh.fit.eshopapi.repository;

import iuh.fit.eshopapi.model.Product;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 11/2/2026
 */

public interface ProductRepository extends ListCrudRepository<Product, Long> {

    // Kiểm tra danh mục có sản phẩm hay không
    @Query("""
        SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END
        FROM product
        WHERE category_id = :categoryId
    """)
    boolean existsByCategoryId(Long categoryId);

    // Lấy danh sách sản phẩm theo danh mục
    @Query("""
        SELECT *
        FROM product
        WHERE category_id = :categoryId
    """)
    List<Product> findByCategoryId(Long categoryId);
}