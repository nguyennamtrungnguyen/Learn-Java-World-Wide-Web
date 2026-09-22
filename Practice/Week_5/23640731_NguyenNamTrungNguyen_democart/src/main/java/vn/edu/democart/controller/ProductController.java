package vn.edu.democart.controller;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/22/2026
 */

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.democart.model.Product;
import vn.edu.democart.repository.ProductRepository;
import vn.edu.democart.repository.impl.ProductRepositoryImpl;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet({"/product", "/products"})
public class ProductController extends HttpServlet{
    private ProductRepository productRepository;

    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {

        productRepository = new ProductRepositoryImpl(dataSource) {
        };
    }

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        String idStr = req.getParameter("id");

        // Xem chi tiết
        if (idStr != null && !idStr.isBlank()) {

            try {

                int id = Integer.parseInt(idStr);

                Product product =
                        productRepository.getProductById(id);

                if (product == null) {

                    resp.sendError(
                            HttpServletResponse.SC_NOT_FOUND,
                            "Product not found"
                    );

                    return;
                }

                req.setAttribute(
                        "product",
                        product
                );

                req.getRequestDispatcher(
                        "/product-detail.jsp"
                ).forward(req, resp);

                return;

            } catch (NumberFormatException e) {

                resp.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Invalid product id"
                );

                return;
            }
        }

        // Hiển thị danh sách
        List<Product> products =
                productRepository.getAllProducts();

        req.setAttribute(
                "products",
                products
        );

        req.getRequestDispatcher(
                "/product-list.jsp"
        ).forward(req, resp);
    }
}
