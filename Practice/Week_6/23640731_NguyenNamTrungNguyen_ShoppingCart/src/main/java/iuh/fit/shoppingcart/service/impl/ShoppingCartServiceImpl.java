package iuh.fit.shoppingcart.service.impl;

import iuh.fit.shoppingcart.dto.AddCartResponseDTO;
import iuh.fit.shoppingcart.dto.BillItemDTO;
import iuh.fit.shoppingcart.dto.CheckoutSummaryDTO;
import iuh.fit.shoppingcart.dto.CustomerBillDTO;
import iuh.fit.shoppingcart.model.Product;
import iuh.fit.shoppingcart.model.ShoppingCart;
import iuh.fit.shoppingcart.repository.ProductRepository;
import iuh.fit.shoppingcart.repository.ShoppingCartRepository;
import iuh.fit.shoppingcart.service.ShoppingCartService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Inject
    private ShoppingCartRepository cartRepository;

    @Inject
    private ProductRepository productRepository;

    @Override
    public List<ShoppingCart> getAllCarts() { return cartRepository.findAll(); }

    @Override
    public ShoppingCart getCartById(int id) { return cartRepository.findById(id); }

    @Override
    public boolean createCart(ShoppingCart cart) { return cartRepository.save(cart); }

    @Override
    public boolean updateCart(ShoppingCart cart) { return cartRepository.update(cart); }

    @Override
    public boolean deleteCart(int id) { return cartRepository.delete(id); }

    // ---------- Yeu cau 1 ----------
    @Override
    public CustomerBillDTO calculateCustomerBill(String customerName) {
        List<ShoppingCart> carts = cartRepository.findByCustomerName(customerName);
        List<BillItemDTO> items = new ArrayList<>();
        double subTotal = 0;

        for (ShoppingCart c : carts) {
            Product p = productRepository.findById(c.getProductId());
            if (p == null) continue;
            double itemTotal = p.getPrice() * c.getQuantity();
            items.add(new BillItemDTO(c.getId(), p.getName(), p.getPrice(), c.getQuantity(), itemTotal));
            subTotal += itemTotal;
        }

        double discount = subTotal > 2000 ? subTotal * 0.1 : 0.0;
        double finalTotal = subTotal - discount;
        return new CustomerBillDTO(customerName, items, round2(subTotal), round2(discount), round2(finalTotal));
    }

    // ---------- Yeu cau 2 ----------
    @Override
    public AddCartResponseDTO addItemToCart(ShoppingCart cart) {
        Product product = productRepository.findById(cart.getProductId());
        if (product == null) {
            throw new IllegalArgumentException("Sản phẩm không tồn tại!");
        }
        if (cart.getQuantity() <= 0) {
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0!");
        }
        double estimatedTotal = product.getPrice() * cart.getQuantity();
        if (estimatedTotal > 5000.0) {
            throw new IllegalStateException("Giá trị đơn hàng vượt quá hạn mức cho phép ($5,000)!");
        }
        if (!cartRepository.save(cart)) {
            throw new IllegalStateException("Không thể lưu giỏ hàng!");
        }
        return new AddCartResponseDTO(cart.getId(), product.getName(), cart.getQuantity(), estimatedTotal);
    }

    // ---------- Yeu cau 4 ----------
    @Override
    public CheckoutSummaryDTO checkout(String customerName) {
        List<ShoppingCart> carts = cartRepository.findByCustomerName(customerName);
        if (carts.isEmpty()) {
            throw new IllegalArgumentException("Giỏ hàng của khách hàng trống hoặc không tồn tại!");
        }

        int totalQuantity = 0;
        double totalAmount = 0;
        for (ShoppingCart c : carts) {
            Product p = productRepository.findById(c.getProductId());
            if (p == null) continue;
            totalQuantity += c.getQuantity();
            totalAmount += p.getPrice() * c.getQuantity();
        }

        cartRepository.deleteByCustomerName(customerName);
        return new CheckoutSummaryDTO(customerName, carts.size(), totalQuantity,
                round2(totalAmount), "CHECKOUT_COMPLETED_AND_CART_CLEARED");
    }

    private static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}
