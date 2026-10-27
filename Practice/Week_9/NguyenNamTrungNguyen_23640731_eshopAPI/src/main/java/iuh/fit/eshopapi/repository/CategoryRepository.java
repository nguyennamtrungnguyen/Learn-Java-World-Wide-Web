package iuh.fit.eshopapi.repository;


import iuh.fit.eshopapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Kiểm tra tên danh mục đã tồn tại, không phân biệt hoa thường
    boolean existsByNameIgnoreCase(String name);

    // Kiểm tra tên danh mục đã tồn tại ở bản ghi khác khi cập nhật
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);

    boolean existsByNameIgnoreCaseAndCategoryIdNot(String trim, Long id);
}