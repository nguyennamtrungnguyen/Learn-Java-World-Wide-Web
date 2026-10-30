package iuh.fit.eshopapi.controller;

import iuh.fit.eshopapi.request.ProductRequest;
import iuh.fit.eshopapi.response.PageResponse;
import iuh.fit.eshopapi.response.ProductResponse;
import iuh.fit.eshopapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // P1: GET /api/products
    // Tìm kiếm + phân trang + sắp xếp
    @GetMapping
    public ResponseEntity<PageResponse<ProductResponse>> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort
    ) {
        return ResponseEntity.ok(
                productService.search(keyword, categoryId, minPrice, maxPrice, page, size, sort)
        );
    }

    // P2: GET /api/products/{id}
    // Chi tiết sản phẩm
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.one(id));
    }

    // P3: POST /api/products
    // Thêm sản phẩm
    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @Valid @RequestBody ProductRequest request
    ) {
        ProductResponse created = productService.create(request);
        URI location = URI.create("/api/products/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    // P4: PUT /api/products/{id}
    // Sửa toàn bộ thông tin sản phẩm
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request
    ) {
        return ResponseEntity.ok(productService.update(id, request));
    }

    // P5: DELETE /api/products/{id}
    // Xoá sản phẩm
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // N1: PATCH /api/products/{id}/stock
    // Tăng/giảm tồn kho
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductResponse> updateStock(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> body
    ) {
        Integer delta = body.get("delta");
        if (delta == null) {
            throw new IllegalArgumentException("delta không được để trống");
        }
        return ResponseEntity.ok(productService.updateStock(id, delta));
    }
}
