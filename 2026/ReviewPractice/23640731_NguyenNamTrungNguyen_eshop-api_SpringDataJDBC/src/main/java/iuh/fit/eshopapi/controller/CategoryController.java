package iuh.fit.eshopapi.controller;

import iuh.fit.eshopapi.dto.CategoryStats;
import iuh.fit.eshopapi.model.Category;
import iuh.fit.eshopapi.model.Product;
import iuh.fit.eshopapi.service.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 11/2/2026
 */
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // Lấy tất cả danh mục
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    // Lấy danh mục theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    // Thêm danh mục
    @PostMapping
    public ResponseEntity<Category> create(
            @RequestBody Category category) {

        Category saved = categoryService.create(category);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    // Cập nhật danh mục
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(
            @PathVariable Long id,
            @RequestBody Category category) {

        return ResponseEntity.ok(
                categoryService.update(id, category)
        );
    }

    // Xóa danh mục
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Lấy sản phẩm theo danh mục
    @GetMapping("/{id}/products")
    public ResponseEntity<List<Product>> getProducts(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                categoryService.findProductsByCategoryId(id)
        );
    }

    // Thống kê danh mục
    @GetMapping("/stats")
    public ResponseEntity<List<CategoryStats>> getStats() {
        return ResponseEntity.ok(categoryService.getCategoryStats());
    }
}