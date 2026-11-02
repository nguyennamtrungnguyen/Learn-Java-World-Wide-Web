package iuh.fit.eshopapi.service;

import iuh.fit.eshopapi.model.Product;
import iuh.fit.eshopapi.repository.CategoryRepository;
import iuh.fit.eshopapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
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
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // Lấy tất cả sản phẩm
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // Lấy sản phẩm theo ID
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy sản phẩm có ID: " + id
                        ));
    }

    // Thêm sản phẩm
    public Product create(Product product) {

        validateCategory(product.getCategoryId());

        product.setId(null);

        if (product.getCreatedAt() == null) {
            product.setCreatedAt(LocalDateTime.now());
        }

        return productRepository.save(product);
    }

    // Cập nhật sản phẩm
    public Product update(Long id, Product product) {

        Product existingProduct = findById(id);
        validateCategory(product.getCategoryId());
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setImageUrl(product.getImageUrl());
        existingProduct.setCategoryId(product.getCategoryId());

        // Giữ nguyên createdAt của sản phẩm cũ
        return productRepository.save(existingProduct);
    }

    // Xóa sản phẩm
    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

    // Kiểm tra danh mục tồn tại
    private void validateCategory(Long categoryId) {
        if (categoryId == null || categoryRepository.findById(categoryId).isEmpty()) {
            throw new RuntimeException("Danh mục không tồn tại với ID: " + categoryId);
        }
    }
}