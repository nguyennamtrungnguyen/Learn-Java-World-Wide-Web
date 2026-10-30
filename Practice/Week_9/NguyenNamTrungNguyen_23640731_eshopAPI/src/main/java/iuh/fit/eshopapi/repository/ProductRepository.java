package iuh.fit.eshopapi.repository;

import iuh.fit.eshopapi.model.Product;
import iuh.fit.eshopapi.response.CategoryStatsResponse;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    boolean existsByCategoryId(Long categoryId);

    List<Product> findByCategoryId(Long categoryId);

    @Query(
            value = "select count(*) > 0 " +
                    "from order_detail where product_id = :id",
            nativeQuery = true
    )
    boolean existsInOrderDetail(@Param("id") Long id);

    @Query("""
        select p from Product p
        where (:keyword is null
            or lower(p.name) like lower(concat('%', :keyword, '%')))
        and (:categoryId is null
            or p.category.id = :categoryId)
        and (:minPrice is null
            or p.price >= :minPrice)
        and (:maxPrice is null
            or p.price <= :maxPrice)
    """)
    Page<Product> search(
            @Param("keyword") String keyword,
            @Param("categoryId") Long categoryId,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            Pageable pageable
    );

    @Query("""
        select new iuh.fit.eshopapi.response.CategoryStatsResponse(
            c.id,
            c.name,
            count(p.id),
            coalesce(sum(p.price * p.quantity), 0)
        )
        from Category c
        left join Product p on p.category = c
        group by c.id, c.name
        order by c.name
    """)
    List<CategoryStatsResponse> getCategoryStats();
}
