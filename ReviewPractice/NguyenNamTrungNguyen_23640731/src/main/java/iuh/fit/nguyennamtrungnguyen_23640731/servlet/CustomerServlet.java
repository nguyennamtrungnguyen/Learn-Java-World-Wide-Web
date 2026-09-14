package iuh.fit.nguyennamtrungnguyen_23640731.servlet;

import iuh.fit.nguyennamtrungnguyen_23640731.model.Customer;
import iuh.fit.nguyennamtrungnguyen_23640731.service.CustomerList;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/14/2026
 */

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {

    @Inject
    private CustomerList customerList;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("customers", customerList.getCustomers());

        req.getRequestDispatcher("/customerlist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");

        int id = customerList.getCustomers().size() + 1;

        Customer customer = new Customer(id, name, email, address);

        customerList.addCustomer(customer);

        resp.sendRedirect(req.getContextPath() + "/customers");
    }
}
