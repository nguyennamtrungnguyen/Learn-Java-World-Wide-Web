
package iuh.fit.repository;

import iuh.fit.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Kiểm tra Category có sản phẩm hay không
    boolean existsByCategoryId(Long categoryId);

    // Lấy sản phẩm theo Category ID
    @EntityGraph(attributePaths = "category")
    List<Product> findByCategoryId(Long categoryId);

    // Lấy tất cả sản phẩm kèm Category
    @Override
    @EntityGraph(attributePaths = "category")
    List<Product> findAll();

    // Tìm sản phẩm theo ID kèm Category
    @Override
    @EntityGraph(attributePaths = "category")
    Optional<Product> findById(Long id);

    // Lấy sản phẩm có giá từ 1 triệu trở lên
    @EntityGraph(attributePaths = "category")
    @Query("""
        SELECT p FROM Product p
        WHERE p.price >= 1000000
    """)
    List<Product> findProductByCategoryPrice();
}
