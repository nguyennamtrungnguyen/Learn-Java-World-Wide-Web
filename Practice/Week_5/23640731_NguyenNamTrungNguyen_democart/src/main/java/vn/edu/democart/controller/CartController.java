package vn.edu.democart.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.democart.model.Cart;
import vn.edu.democart.model.Product;
import vn.edu.democart.repository.ProductRepository;
import vn.edu.democart.repository.impl.ProductRepositoryImpl;

import javax.sql.DataSource;
import java.io.IOException;
/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/22/2026
 */
@WebServlet("/cart")
public class CartController extends HttpServlet{
    private ProductRepository productRepository;

    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {

        productRepository = new ProductRepositoryImpl(dataSource);
    }

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher(
                "/cart.jsp"
        ).forward(req, resp);
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        HttpSession session =
                req.getSession();

        Cart cart =
                (Cart) session.getAttribute("cart");

        if (cart == null) {

            cart = new Cart();

            session.setAttribute(
                    "cart",
                    cart
            );
        }

        String action =
                req.getParameter("action");

        try {

            // ADD
            if ("add".equals(action)) {

                int id =
                        Integer.parseInt(
                                req.getParameter("id")
                        );

                Product product =
                        productRepository.getProductById(id);

                if (product != null) {

                    cart.addProduct(product);
                }
            }

            // UPDATE
            else if ("update".equals(action)) {

                int id =
                        Integer.parseInt(
                                req.getParameter("productId")
                        );

                int quantity =
                        Integer.parseInt(
                                req.getParameter("quantity")
                        );

                cart.updateQuantity(
                        id,
                        quantity
                );
            }

            // REMOVE
            else if ("remove".equals(action)) {

                int id =
                        Integer.parseInt(
                                req.getParameter("productId")
                        );

                cart.removeProduct(id);
            }

            // CLEAR
            else if ("clear".equals(action)) {

                cart.clear();
            }

            resp.sendRedirect(
                    req.getContextPath() + "/cart"
            );

        } catch (NumberFormatException e) {

            throw new ServletException(
                    "Invalid number",
                    e
            );
        }
    }
}
