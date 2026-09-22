package iuh.fit.nguyennamtrungnguyen_23640731_onlan2.servlet;

import iuh.fit.nguyennamtrungnguyen_23640731_onlan2.model.Customer;
import iuh.fit.nguyennamtrungnguyen_23640731_onlan2.service.CustomerList;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/15/2026
 */

@WebServlet("/customers")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 15
)
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
        int id = customerList.getCustomers().size() + 1;
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String[] hobbies = req.getParameterValues("hobbies");
        Part avatarPart = req.getPart("avatar");
        String fileName = avatarPart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("uploads");

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();
        avatarPart.write(uploadPath + File.separator + fileName);

        Customer customer = new Customer(id, fileName, name, email, address, hobbies);

        customerList.addCustomer(customer);
        resp.sendRedirect(req.getContextPath()+"/customers");
    }
}
