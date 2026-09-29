package iuh.fit.shoppingcart.repository;

import iuh.fit.shoppingcart.model.ShoppingCart;

import java.util.List;
import java.util.Map;

public interface ShoppingCartRepository {
    List<ShoppingCart> findAll();
    ShoppingCart findById(int id);
    boolean save(ShoppingCart cart);
    boolean update(ShoppingCart cart);
    boolean delete(int id);

    // Yeu cau 1, 4
    List<ShoppingCart> findByCustomerName(String customerName);
    int deleteByCustomerName(String customerName);

    // Yeu cau 3, 5: productId -> SUM(quantity)
    Map<Integer, Integer> sumQuantityByProduct();
}
