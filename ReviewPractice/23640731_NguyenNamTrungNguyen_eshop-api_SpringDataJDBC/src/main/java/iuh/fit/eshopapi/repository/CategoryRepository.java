package iuh.fit.eshopapi.repository;

import iuh.fit.eshopapi.dto.CategoryStats;
import iuh.fit.eshopapi.model.Category;
import iuh.fit.eshopapi.model.Product;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 11/2/2026
 */
public interface CategoryRepository extends ListCrudRepository<Category, Long> {

    // Kiểm tra tên danh mục đã tồn tại
    @Query("""
        SELECT COUNT(*)
        FROM category
        WHERE LOWER(name) = LOWER(:name)
    """)
    Long countByNameIgnoreCase(String name);

    // Kiểm tra tên danh mục đã tồn tại ở danh mục khác
    @Query("""
        SELECT COUNT(*)
        FROM category
        WHERE LOWER(name) = LOWER(:name)
          AND id <> :id
    """)
    Long countByNameIgnoreCaseAndIdNot(String name, Long id);

    // Kiểm tra danh mục có sản phẩm hay không
    @Query("""
        SELECT COUNT(*)
        FROM product
        WHERE category_id = :categoryId
    """)
    Long countProductsByCategoryId(Long categoryId);

    // Lấy sản phẩm thuộc danh mục
    @Query("""
        SELECT *
        FROM product
        WHERE category_id = :categoryId
    """)
    List<Product> findProductsByCategoryId(Long categoryId);

    // Thống kê danh mục
    @Query("""
    SELECT
        c.id AS category_id,
        c.name AS category_name,
        COUNT(p.id) AS product_count,
        COALESCE(SUM(p.price * p.quantity), 0) AS total_inventory_value
    FROM category c
    LEFT JOIN product p ON p.category_id = c.id
    GROUP BY c.id, c.name
    ORDER BY c.name
""")
    List<CategoryStats> getCategoryStats();
}
