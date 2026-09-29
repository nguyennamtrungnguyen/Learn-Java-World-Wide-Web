package iuh.fit.shoppingcart.service;

import iuh.fit.shoppingcart.dto.AddCartResponseDTO;
import iuh.fit.shoppingcart.dto.CheckoutSummaryDTO;
import iuh.fit.shoppingcart.dto.CustomerBillDTO;
import iuh.fit.shoppingcart.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> getAllCarts();
    ShoppingCart getCartById(int id);
    boolean createCart(ShoppingCart cart);
    boolean updateCart(ShoppingCart cart);
    boolean deleteCart(int id);

    CustomerBillDTO calculateCustomerBill(String customerName);   // Yeu cau 1
    AddCartResponseDTO addItemToCart(ShoppingCart cart);          // Yeu cau 2
    CheckoutSummaryDTO checkout(String customerName);             // Yeu cau 4
}
