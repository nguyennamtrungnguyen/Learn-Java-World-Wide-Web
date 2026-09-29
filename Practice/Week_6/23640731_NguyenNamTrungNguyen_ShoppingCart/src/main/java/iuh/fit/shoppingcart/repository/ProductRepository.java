package iuh.fit.shoppingcart.repository;
import iuh.fit.shoppingcart.model.Product;

import java.util.List;
public interface ProductRepository {
    List<Product> findAll();
    Product findById(int id);
    boolean save(Product product);
    boolean update(Product product);
    boolean delete(int id);
}