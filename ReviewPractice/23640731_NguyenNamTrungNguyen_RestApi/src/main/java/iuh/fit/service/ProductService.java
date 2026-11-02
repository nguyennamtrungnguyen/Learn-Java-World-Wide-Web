
package iuh.fit.service;

import iuh.fit.dto.CategoryProductDTO;
import iuh.fit.model.Category;
import iuh.fit.model.Product;
import iuh.fit.repository.CategoryRepository;
import iuh.fit.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // 1. Lấy tất cả sản phẩm
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // 2. Tìm sản phẩm theo ID
    public Product findById(Long id) {
        return getProduct(id);
    }

    // 3. Thêm sản phẩm
    public Product create(Product product) {

        if (product.getCategory() == null ||
                product.getCategory().getId() == null) {
            throw new RuntimeException("Vui lòng chọn Category");
        }

        Long categoryId = product.getCategory().getId();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy Category")
                );

        product.setId(null);
        product.setCategory(category);

        return productRepository.save(product);
    }

    // 4. Cập nhật sản phẩm
    public Product update(Long id, Product request) {

        Product product = getProduct(id);

        if (request.getCategory() == null ||
                request.getCategory().getId() == null) {
            throw new RuntimeException("Vui lòng chọn Category");
        }

        Long categoryId = request.getCategory().getId();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy Category")
                );

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setImageUrl(request.getImageUrl());
        product.setCategory(category);

        return productRepository.save(product);
    }

    // 5. Xóa sản phẩm
    public void delete(Long id) {
        Product product = getProduct(id);
        productRepository.delete(product);
    }

    // 6. Tìm sản phẩm theo Category
    public List<Product> findByCategoryId(Long categoryId) {

        if (!categoryRepository.existsById(categoryId)) {
            throw new RuntimeException("Không tìm thấy Category");
        }

        return productRepository.findByCategoryId(categoryId);
    }

    // 7. Lấy sản phẩm giá >= 1 triệu và gom theo Category
    public List<CategoryProductDTO> findProductByCategoryPrice() {

        List<Product> products =
                productRepository.findProductByCategoryPrice();

        Map<Long, CategoryProductDTO> map = new LinkedHashMap<>();

        for (Product p : products) {

            Category category = p.getCategory();
            Long categoryId = category.getId();

            // Nếu Category chưa có thì tạo mới
            if (!map.containsKey(categoryId)) {
                map.put(
                        categoryId,
                        new CategoryProductDTO(category, new ArrayList<>())
                );
            }

            // Thêm Product vào danh sách của Category
            map.get(categoryId).getProducts().add(p);
        }

        return new ArrayList<>(map.values());
    }

    // Hàm dùng chung để tìm Product
    private Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy sản phẩm")
                );
    }
}
