package iuh.fit.eshopapi.controller;

import iuh.fit.eshopapi.model.Product;
import iuh.fit.eshopapi.request.CategoryRequest;
import iuh.fit.eshopapi.response.CategoryResponse;
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

    // C1
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    // C2
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(
            @PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    // C3
    @PostMapping
    public ResponseEntity<CategoryResponse> create(
            @Valid @RequestBody CategoryRequest request) {
        CategoryResponse created = categoryService.create(request);

        URI location = URI.create("/api/categories/" + created.id());

        return ResponseEntity.created(location).body(created);
    }

    // C4
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(
                categoryService.update(id, request));
    }

    // C5
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // C6
    @GetMapping("/{id}/products")
    public ResponseEntity<List<Product>> findProducts(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                categoryService.findProducts(id));
    }
}
