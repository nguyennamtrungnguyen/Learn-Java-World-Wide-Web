package iuh.fit.eshop.service;

import iuh.fit.eshop.exception.NotFoundException;
import iuh.fit.eshop.model.Category;
import iuh.fit.eshop.model.Product;
import iuh.fit.eshop.repository.CategoryRepository;
import iuh.fit.eshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public Page<Product> search(String keyword, Long categoryId, int page, int
            size) {
        String kw = StringUtils.hasText(keyword) ? keyword.trim() : null;
        return productRepository.search(kw, categoryId,
                PageRequest.of(Math.max(page, 0), size,
                        Sort.by("id").descending()));
    }
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm id = " + id));
    }
    @Transactional
    public Product save(Product product, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy danh mục id = " + categoryId));
        product.setCategory(category);
        return productRepository.save(product);  // id == null → INSERT, id != null → UPDATE
    }
    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
// vướng khoá ngoại order_detail.product_id
            throw new IllegalStateException("Sản phẩm đã có trong đơn hàng, không thể xoá");
        }
    }
}