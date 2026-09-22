package vn.edu.democart.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/22/2026
 */
public class Cart implements Serializable{
    private final List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return items;
    }

    // Thêm sản phẩm
    public void addProduct(Product product) {

        if (product == null) {
            return;
        }

        for (CartItem item : items) {

            if (item.getProduct().getId() == product.getId()) {

                item.setQuantity(
                        item.getQuantity() + 1
                );

                return;
            }
        }

        items.add(
                new CartItem(product, 1)
        );
    }

    // Cập nhật số lượng
    public void updateQuantity(int productId, int quantity) {

        for (CartItem item : items) {

            if (item.getProduct().getId() == productId) {

                if (quantity > 0) {

                    item.setQuantity(quantity);

                } else {

                    removeProduct(productId);
                }

                return;
            }
        }
    }

    // Xóa một sản phẩm
    public void removeProduct(int productId) {

        items.removeIf(
                item ->
                        item.getProduct().getId() == productId
        );
    }

    // Xóa toàn bộ
    public void clear() {

        items.clear();
    }

    // Tính tổng tiền
    public double getTotal() {

        double total = 0;

        for (CartItem item : items) {

            total += item.getSubtotal();
        }

        return total;
    }

    public boolean isEmpty() {

        return items.isEmpty();
    }
}
