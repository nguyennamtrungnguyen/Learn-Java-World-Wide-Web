package iuh.fit.shoppingcart.service;

import iuh.fit.shoppingcart.dto.RepriceReportDTO;
import iuh.fit.shoppingcart.dto.RevenueShareDTO;
import iuh.fit.shoppingcart.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(int id);
    boolean createProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(int id);

    List<RepriceReportDTO> applyDynamicRepricing();      // Yeu cau 3
    List<RevenueShareDTO> getRevenueShareReport();       // Yeu cau 5
}
