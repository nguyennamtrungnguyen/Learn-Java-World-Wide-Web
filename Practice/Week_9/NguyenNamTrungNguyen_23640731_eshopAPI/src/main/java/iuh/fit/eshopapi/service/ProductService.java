package iuh.fit.eshopapi.service;

import iuh.fit.eshopapi.exception.ConflictException;
import iuh.fit.eshopapi.exception.NotFoundException;
import iuh.fit.eshopapi.model.Category;
import iuh.fit.eshopapi.model.Product;
import iuh.fit.eshopapi.repository.CategoryRepository;
import iuh.fit.eshopapi.repository.ProductRepository;
import iuh.fit.eshopapi.request.ProductRequest;
import iuh.fit.eshopapi.response.PageResponse;
import iuh.fit.eshopapi.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;

@Service
@Transactional
public class ProductService {

    private final ProductRepository products;
    private final CategoryRepository cats;

    // Những thuộc tính được phép sắp xếp
    private static final Set<String> SORTABLE = Set.of(
            "id",
            "name",
            "price",
            "quantity",
            "createdAt"
    );

    public ProductService(
            ProductRepository products,
            CategoryRepository cats
    ) {
        this.products = products;
        this.cats = cats;
    }

    // Entity -> DTO
    private ProductResponse dto(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getQuantity(),
                p.getImageUrl(),
                p.getCategory().getId(),
                p.getCategory().getName(),
                p.getCreatedAt()
        );
    }

    // Tìm danh mục theo ID
    private Category category(Long id) {
        return cats.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Không tìm thấy danh mục id = " + id
                        )
                );
    }

    // Tìm kiếm + phân trang + sắp xếp
    @Transactional(readOnly = true)
    public PageResponse<ProductResponse> search(
            String keyword,
            Long categoryId,
            BigDecimal min,
            BigDecimal max,
            int page,
            int size,
            String sort
    ) {

        if (page < 0) {
            throw new IllegalArgumentException(
                    "page phải >= 0"
            );
        }

        if (size < 1 || size > 100) {
            throw new IllegalArgumentException(
                    "size phải từ 1 đến 100"
            );
        }

        if (min != null && max != null
                && min.compareTo(max) > 0) {
            throw new IllegalArgumentException(
                    "minPrice không được lớn hơn maxPrice"
            );
        }

        // Phân tích sort
        String[] a = sort.split(",");

        if (a.length > 2 || !SORTABLE.contains(a[0])) {
            throw new IllegalArgumentException(
                    "Trường sort không hợp lệ"
            );
        }

        Sort.Direction direction = Sort.Direction.ASC;

        if (a.length == 2) {
            try {
                direction = Sort.Direction.fromString(a[1]);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "sort chỉ nhận asc hoặc desc"
                );
            }
        }

        // Tạo Pageable
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(direction, a[0])
        );

        // Gọi Repository
        Page<ProductResponse> result = products.search(
                keyword == null || keyword.isBlank()
                        ? null : keyword.trim(),
                categoryId,
                min,
                max,
                pageable
        ).map(this::dto);

        // Trả PageResponse
        return new PageResponse<>(
                result.getContent(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }

    // Lấy sản phẩm theo ID
    @Transactional(readOnly = true)
    public ProductResponse one(Long id) {
        Product p = products.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Không tìm thấy sản phẩm id = " + id
                        )
                );

        return dto(p);
    }

    // Thêm sản phẩm
    public ProductResponse create(ProductRequest r) {
        Product p = new Product();
        apply(p, r);
        return dto(products.save(p));
    }

    // Cập nhật sản phẩm
    public ProductResponse update(
            Long id,
            ProductRequest r
    ) {
        Product p = products.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Không tìm thấy sản phẩm id = " + id
                        )
                );

        apply(p, r);
        return dto(products.save(p));
    }

    // Gán dữ liệu từ Request vào Entity
    private void apply(Product p, ProductRequest r) {
        p.setName(r.name().trim());
        p.setDescription(r.description());
        p.setPrice(r.price());
        p.setQuantity(r.quantity());
        p.setImageUrl(r.imageUrl());
        p.setCategory(category(r.categoryId()));
    }

    // Xóa sản phẩm
    public void delete(Long id) {
        Product p = products.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Không tìm thấy sản phẩm id = " + id
                        )
                );

        if (products.existsInOrderDetail(id)) {
            throw new ConflictException(
                    "Không thể xoá sản phẩm đã có trong order_detail"
            );
        }

        products.delete(p);
    }

    // N1: Cập nhật tồn kho (tăng/giảm tồn kho)
    public ProductResponse updateStock(Long id, int delta) {
        Product p = products.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Không tìm thấy sản phẩm id = " + id
                        )
                );

        int newQuantity = p.getQuantity() + delta;
        if (newQuantity < 0) {
            throw new ConflictException(
                    "Tồn kho không đủ (sau khi trừ < 0)"
            );
        }

        p.setQuantity(newQuantity);
        return dto(products.save(p));
    }
}
