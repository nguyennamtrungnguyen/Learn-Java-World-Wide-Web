package iuh.fit.eshopapi.controller;

import iuh.fit.eshopapi.request.CategoryRequest;
import iuh.fit.eshopapi.response.CategoryResponse;
import iuh.fit.eshopapi.response.CategoryStatsResponse;
import iuh.fit.eshopapi.response.ProductResponse;
import iuh.fit.eshopapi.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // C1: GET /api/categories
    // Lấy tất cả danh mục
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    // N3: GET /api/categories/stats
    // Thống kê danh mục
    @GetMapping("/stats")
    public ResponseEntity<List<CategoryStatsResponse>> stats() {
        return ResponseEntity.ok(categoryService.stats());
    }

    // C2: GET /api/categories/{id}
    // Lấy danh mục theo ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    // C3: POST /api/categories
    // Thêm danh mục
    @PostMapping
    public ResponseEntity<CategoryResponse> create(
            @Valid @RequestBody CategoryRequest request
    ) {
        CategoryResponse created = categoryService.create(request);
        URI location = URI.create("/api/categories/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    // C4: PUT /api/categories/{id}
    // Cập nhật danh mục
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request
    ) {
        return ResponseEntity.ok(categoryService.update(id, request));
    }

    // C5: DELETE /api/categories/{id}
    // Xóa danh mục
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // C6: GET /api/categories/{id}/products
    // Lấy sản phẩm theo danh mục
    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductResponse>> findProducts(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findProducts(id));
    }
}
