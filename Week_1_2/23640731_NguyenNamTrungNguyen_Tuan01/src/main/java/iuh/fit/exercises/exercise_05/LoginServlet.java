package iuh.fit.exercises.exercise_05;

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
 * @since 8/18/2026
 */
@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("hi");
        req.getRequestDispatcher("/LoginPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");


        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean ok = "admin".equalsIgnoreCase(username) && "123".equalsIgnoreCase(password);
        if (ok) {
            req.getSession().setAttribute("username", username);
            resp.sendRedirect(req.getContextPath() + "/section5/Home.jsp");
            return;
        }

        req.setAttribute("error", "Mật khẩu hoặc tài khoản không hợp lệ");
        req.getRequestDispatcher("/section5/LoginPage.jsp").forward(req, resp);
    }
}