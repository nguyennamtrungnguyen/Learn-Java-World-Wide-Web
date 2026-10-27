package iuh.fit.eshopapi.repository;

import iuh.fit.eshopapi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Kiểm tra danh mục có sản phẩm hay không
    boolean existsByCategoryId(Long categoryId);

    // Lấy danh sách sản phẩm theo danh mục
    List<Product> findByCategoryId(Long categoryId);

    // Tìm kiếm theo từ khóa, danh mục và khoảng giá
    @Query("""
        SELECT p
        FROM Product p
        WHERE
            (:keyword IS NULL
                OR LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')))
        AND (:categoryId IS NULL
                OR p.category.categoryId = :categoryId)
        AND (:minPrice IS NULL
                OR p.price >= :minPrice)
        AND (:maxPrice IS NULL
                OR p.price <= :maxPrice)
        """)
    Page<Product> search(
            @Param("keyword") String keyword,
            @Param("categoryId") Long categoryId,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            Pageable pageable
    );

    boolean existsByCategory_CategoryId(Integer id);

    List<Product> findByCategory_CategoryId(Integer id);
}