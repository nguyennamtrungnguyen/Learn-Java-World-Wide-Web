
package iuh.fit.controller;

import iuh.fit.dto.CategoryProductDTO;
import iuh.fit.model.Product;
import iuh.fit.service.ProductService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 1. Lấy tất cả sản phẩm
    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    // 2. Tìm sản phẩm theo ID
    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    // 3. Thêm sản phẩm
    @PostMapping
    public ResponseEntity<Product> create(
            @RequestBody Product product) {

        Product saved = productService.create(product);

        return ResponseEntity
                .created(URI.create("/products/" + saved.getId()))
                .body(saved);
    }

    // 4. Cập nhật sản phẩm
    @PutMapping("/{id}")
    public Product update(
            @PathVariable Long id,
            @RequestBody Product product) {

        return productService.update(id, product);
    }

    // 5. Xóa sản phẩm
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        productService.delete(id);

        return ResponseEntity.noContent().build();
    }

    // 6. Tìm sản phẩm theo Category
    @GetMapping("/category/{categoryId}")
    public List<Product> findByCategoryId(
            @PathVariable Long categoryId) {

        return productService.findByCategoryId(categoryId);
    }

    // 7. Thống kê sản phẩm theo Category, giá >= 1 triệu
    @GetMapping("/category-products/price")
    public List<CategoryProductDTO> findProductByCategoryPrice() {

        return productService.findProductByCategoryPrice();
    }
}
