package iuh.fit.eshopapi.service;

import iuh.fit.eshopapi.dto.CategoryStats;
import iuh.fit.eshopapi.model.Category;
import iuh.fit.eshopapi.model.Product;
import iuh.fit.eshopapi.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 11/2/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // Lấy tất cả danh mục
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // Lấy danh mục theo ID
    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy danh mục có ID: " + id));
    }

    // Thêm danh mục
    public Category create(Category category) {

        if (categoryRepository.countByNameIgnoreCase(
                category.getName()) > 0) {
            throw new RuntimeException("Tên danh mục đã tồn tại");
        }

        category.setId(null);

        return categoryRepository.save(category);
    }

    // Cập nhật danh mục
    public Category update(Long id, Category category) {

        Category existingCategory = findById(id);

        if (categoryRepository.countByNameIgnoreCaseAndIdNot(
                category.getName(), id) > 0) {
            throw new RuntimeException("Tên danh mục đã tồn tại");
        }

        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());

        return categoryRepository.save(existingCategory);
    }

    // Xóa danh mục
    public void delete(Long id) {

        Category category = findById(id);

        if (categoryRepository.countProductsByCategoryId(id) > 0) {
            throw new RuntimeException(
                    "Không thể xóa danh mục đang có sản phẩm");
        }

        categoryRepository.deleteById(id);
    }

    // Lấy sản phẩm theo danh mục
    @Transactional(readOnly = true)
    public List<Product> findProductsByCategoryId(Long categoryId) {

        findById(categoryId);

        return categoryRepository.findProductsByCategoryId(categoryId);
    }

    // Thống kê số lượng sản phẩm và tổng giá trị tồn kho
    @Transactional(readOnly = true)
    public List<CategoryStats> getCategoryStats() {
        return categoryRepository.getCategoryStats();
    }
}