package iuh.fit.nguyennamtrungnguyen.service;

import iuh.fit.nguyennamtrungnguyen.model.Product;

import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/16/2026
 */
public interface ProductService {

    List<Product> getAll();

    Product getById(Long id);

    Product save(Product product);

    Product update(Long id, Product product);

    void delete(Long id);
}
