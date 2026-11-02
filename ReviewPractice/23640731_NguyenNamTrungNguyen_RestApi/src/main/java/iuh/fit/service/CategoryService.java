package iuh.fit.service;

import iuh.fit.dto.CategoryStats;
import iuh.fit.model.Category;
import iuh.fit.repository.CategoryRepository;
import iuh.fit.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll(
                Sort.by("name").ascending()
        );
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category create(Category category) {
        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new RuntimeException("Tên danh mục đã tồn tại");
        }

        category.setId(null);
        return categoryRepository.save(category);
    }

    public Category update(Long id, Category request) {
        Category category = getCategory(id);

        if (categoryRepository.existsByNameIgnoreCaseAndIdNot(
                request.getName(), id)) {
            throw new RuntimeException("Tên danh mục đã tồn tại");
        }

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        Category category = getCategory(id);

        if (productRepository.existsByCategoryId(id)) {
            throw new RuntimeException(
                    "Không thể xóa danh mục đang có sản phẩm"
            );
        }

        categoryRepository.delete(category);
    }

    public List<CategoryStats> getStats() {
        return categoryRepository.getCategoryStats();
    }

    private Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy danh mục")
                );
    }
}