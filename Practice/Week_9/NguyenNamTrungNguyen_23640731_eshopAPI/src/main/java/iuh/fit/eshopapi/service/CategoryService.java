
package iuh.fit.eshopapi.service;

import iuh.fit.eshopapi.exception.ConflictException;
import iuh.fit.eshopapi.exception.NotFoundException;
import iuh.fit.eshopapi.model.Category;
import iuh.fit.eshopapi.repository.CategoryRepository;
import iuh.fit.eshopapi.repository.ProductRepository;
import iuh.fit.eshopapi.request.CategoryRequest;
import iuh.fit.eshopapi.response.CategoryResponse;
import iuh.fit.eshopapi.response.CategoryStatsResponse;
import iuh.fit.eshopapi.response.ProductResponse;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository cats;
    private final ProductRepository products;

    public CategoryService(
            CategoryRepository cats,
            ProductRepository products
    ) {
        this.cats = cats;
        this.products = products;
    }

    // Entity -> DTO
    private CategoryResponse dto(Category c) {
        return new CategoryResponse(
                c.getId(),
                c.getName(),
                c.getDescription()
        );
    }

    // C1: Lấy tất cả danh mục
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {
        return cats.findAll(
                        Sort.by("name").ascending()
                )
                .stream()
                .map(this::dto)
                .toList();
    }

    // C2: Lấy danh mục theo ID
    @Transactional(readOnly = true)
    public CategoryResponse findById(Long id) {

        Category c = cats.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Không tìm thấy danh mục id = " + id
                        )
                );

        return dto(c);
    }

    // C3: Thêm danh mục
    public CategoryResponse create(CategoryRequest r) {

        if (cats.existsByNameIgnoreCase(r.name().trim())) {
            throw new ConflictException(
                    "Tên danh mục đã tồn tại"
            );
        }

        Category c = new Category(
                r.name().trim(),
                r.description()
        );

        return dto(cats.save(c));
    }

    // C4: Cập nhật danh mục
    public CategoryResponse update(
            Long id,
            CategoryRequest r
    ) {

        Category c = cats.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Không tìm thấy danh mục id = " + id
                        )
                );

        if (cats.existsByNameIgnoreCaseAndIdNot(
                r.name().trim(), id
        )) {
            throw new ConflictException(
                    "Tên danh mục đã được sử dụng"
            );
        }

        c.setName(r.name().trim());
        c.setDescription(r.description());

        return dto(cats.save(c));
    }

    // C5: Xóa danh mục
    public void delete(Long id) {

        Category c = cats.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Không tìm thấy danh mục id = " + id
                        )
                );

        if (products.existsByCategoryId(id)) {
            throw new ConflictException(
                    "Không thể xoá danh mục đang có sản phẩm"
            );
        }

        cats.delete(c);
    }

    // C6: Lấy danh sách sản phẩm theo danh mục
    @Transactional(readOnly = true)
    public List<ProductResponse> findProducts(Long id) {

        if (!cats.existsById(id)) {
            throw new NotFoundException(
                    "Không tìm thấy danh mục id = " + id
            );
        }

        return products.findByCategoryId(id)
                .stream()
                .map(p -> new ProductResponse(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice(),
                        p.getQuantity(),
                        p.getImageUrl(),
                        p.getCategory().getId(),
                        p.getCategory().getName(),
                        p.getCreatedAt()
                ))
                .toList();
    }

    // N3: Thống kê danh mục
    @Transactional(readOnly = true)
    public List<CategoryStatsResponse> stats() {
        return products.getCategoryStats();
    }
}
