package iuh.fit.nguyennamtrungnguyen.service.impl;

import iuh.fit.nguyennamtrungnguyen.model.Product;
import iuh.fit.nguyennamtrungnguyen.repository.ProductRepository;
import iuh.fit.nguyennamtrungnguyen.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/16/2026
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy sản phẩm có ID: " + id));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {

        Product existingProduct = getById(id);

        existingProduct.setProname(product.getProname());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setImage(product.getImage());

        return productRepository.save(existingProduct);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}