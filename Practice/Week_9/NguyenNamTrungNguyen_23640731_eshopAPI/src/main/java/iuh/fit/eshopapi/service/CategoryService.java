package iuh.fit.eshopapi.service;

import iuh.fit.eshopapi.exception.ConflictException;
import iuh.fit.eshopapi.exception.NotFoundException;
import iuh.fit.eshopapi.model.Category;
import iuh.fit.eshopapi.model.Product;
import iuh.fit.eshopapi.repository.CategoryRepository;
import iuh.fit.eshopapi.repository.ProductRepository;
import iuh.fit.eshopapi.request.CategoryRequest;
import iuh.fit.eshopapi.response.CategoryResponse;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(
            CategoryRepository categoryRepository,
            ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    private Category findCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tìm thấy danh mục ID: " + id));
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getCategoryId(),
                category.getName(),
                category.getDescription()
        );
    }

    // C1: Danh sách danh mục, sắp xếp theo tên tăng dần
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {
        return categoryRepository
                .findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // C2: Chi tiết danh mục
    @Transactional(readOnly = true)
    public CategoryResponse findById(Long id) {
        return toResponse(findCategory(id));
    }

    // C3: Thêm danh mục
    @Transactional
    public CategoryResponse create(CategoryRequest request) {
        if (categoryRepository.existsByNameIgnoreCase(request.name().trim())) {
            throw new ConflictException("Tên danh mục đã tồn tại");
        }

        Category category = new Category();
        category.setName(request.name().trim());
        category.setDescription(request.description());

        return toResponse(categoryRepository.save(category));
    }

    // C4: Cập nhật danh mục
    @Transactional
    public CategoryResponse update(
            Long id, CategoryRequest request) {
        Category category = findCategory(id);

        if (categoryRepository.existsByNameIgnoreCaseAndCategoryIdNot(
                request.name().trim(), id)) {
            throw new ConflictException(
                    "Tên danh mục đã tồn tại ở danh mục khác");
        }

        category.setName(request.name().trim());
        category.setDescription(request.description());

        return toResponse(categoryRepository.save(category));
    }

    // C5: Xóa danh mục, không cho xóa nếu còn sản phẩm
    @Transactional
    public void delete(Long id) {
        Category category = findCategory(id);

        if (productRepository.existsByCategory_CategoryId(Math.toIntExact(id))) {
            throw new ConflictException(
                    "Không thể xóa danh mục đang có sản phẩm");
        }

        categoryRepository.delete(category);
    }

    // C6: Danh sách sản phẩm theo danh mục
    @Transactional(readOnly = true)
    public List<Product> findProducts(Long id) {
        findCategory(id);
        return productRepository.findByCategory_CategoryId(Math.toIntExact(id));
    }
}
