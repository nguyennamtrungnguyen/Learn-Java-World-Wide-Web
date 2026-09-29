package iuh.fit.shoppingcart.service.impl;

import iuh.fit.shoppingcart.dto.RepriceReportDTO;
import iuh.fit.shoppingcart.dto.RevenueShareDTO;
import iuh.fit.shoppingcart.model.Product;
import iuh.fit.shoppingcart.repository.ProductRepository;
import iuh.fit.shoppingcart.repository.ShoppingCartRepository;
import iuh.fit.shoppingcart.service.ProductService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ShoppingCartRepository cartRepository;

    @Override
    public List<Product> getAllProducts() { return productRepository.findAll(); }

    @Override
    public Product getProductById(int id) { return productRepository.findById(id); }

    @Override
    public boolean createProduct(Product product) { return productRepository.save(product); }

    @Override
    public boolean updateProduct(Product product) { return productRepository.update(product); }

    @Override
    public boolean deleteProduct(int id) { return productRepository.delete(id); }

    // ---------- Yeu cau 3 ----------
    @Override
    public List<RepriceReportDTO> applyDynamicRepricing() {
        Map<Integer, Integer> qtyMap = cartRepository.sumQuantityByProduct();
        List<RepriceReportDTO> report = new ArrayList<>();

        for (Product p : productRepository.findAll()) {
            int totalQty = qtyMap.getOrDefault(p.getId(), 0);
            double oldPrice = p.getPrice();
            double newPrice;
            String adjustment;

            if (totalQty >= 5) {
                newPrice = oldPrice * 1.10;
                adjustment = "+10%";
            } else if (totalQty == 0) {
                newPrice = oldPrice * 0.95;
                adjustment = "-5%";
            } else {
                continue; // khong thay doi
            }

            newPrice = Math.round(newPrice * 100.0) / 100.0;
            p.setPrice(newPrice);
            productRepository.update(p);
            report.add(new RepriceReportDTO(p.getId(), p.getName(), totalQty, oldPrice, newPrice, adjustment));
        }
        return report;
    }

    // ---------- Yeu cau 5 ----------
    @Override
    public List<RevenueShareDTO> getRevenueShareReport() {
        Map<Integer, Integer> qtyMap = cartRepository.sumQuantityByProduct();

        double totalRevenue = 0;
        for (Product p : productRepository.findAll()) {
            totalRevenue += p.getPrice() * qtyMap.getOrDefault(p.getId(), 0);
        }

        List<RevenueShareDTO> list = new ArrayList<>();
        for (Product p : productRepository.findAll()) {
            int units = qtyMap.getOrDefault(p.getId(), 0);
            if (units == 0) continue;
            double revenue = p.getPrice() * units;
            double pct = totalRevenue == 0 ? 0 : revenue / totalRevenue * 100;
            list.add(new RevenueShareDTO(p.getId(), p.getName(), units,
                    Math.round(revenue * 100.0) / 100.0,
                    String.format(Locale.US, "%.2f%%", pct)));
        }

        list.sort(Comparator.comparingDouble(RevenueShareDTO::getRevenueContribution).reversed());
        return list;
    }
}
