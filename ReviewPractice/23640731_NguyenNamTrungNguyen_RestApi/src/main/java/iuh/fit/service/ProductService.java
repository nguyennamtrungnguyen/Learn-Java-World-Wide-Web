package iuh.fit.service;

import iuh.fit.model.Category;
import iuh.fit.model.Product;
import iuh.fit.repository.CategoryRepository;
import iuh.fit.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Page<Product> search(
            String keyword,
            Long categoryId,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            int page,
            int size,
            String sort
    ) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page hoặc size không hợp lệ");
        }

        if (minPrice != null && maxPrice != null
                && minPrice.compareTo(maxPrice) > 0) {
            throw new IllegalArgumentException("minPrice phải nhỏ hơn maxPrice");
        }

        String[] sortParts = sort.split(",");
        String field = sortParts[0];

        Set<String> allowedFields =
                Set.of("id", "name", "price", "quantity", "createdAt");

        if (!allowedFields.contains(field)) {
            throw new IllegalArgumentException("Trường sắp xếp không hợp lệ");
        }

        Sort.Direction direction = sortParts.length > 1
                ? Sort.Direction.fromString(sortParts[1])
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(
                page, size, Sort.by(direction, field)
        );

        return productRepository.search(
                keyword, categoryId, minPrice, maxPrice, pageable
        );
    }

    public Product findById(Long id) {
        return getProduct(id);
    }

    public Product create(Product product) {
        Long categoryId = product.getCategory().getId();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy danh mục")
                );

        product.setId(null);
        product.setCategory(category);
        product.setCreatedAt(null);

        return productRepository.save(product);
    }

    public Product update(Long id, Product request) {
        Product product = getProduct(id);

        Long categoryId = request.getCategory().getId();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy danh mục")
                );

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setImageUrl(request.getImageUrl());
        product.setCategory(category);

        return productRepository.save(product);
    }

    public void delete(Long id) {
        Product product = getProduct(id);
        productRepository.delete(product);
        productRepository.flush();
    }

    private Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy sản phẩm")
                );
    }

    public List<Product> findByCategoryId(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new RuntimeException("Không tìm thấy danh mục");
        }

        return productRepository.findByCategoryId(categoryId);
    }
}