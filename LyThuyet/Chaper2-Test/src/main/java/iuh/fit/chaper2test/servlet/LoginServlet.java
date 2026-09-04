package iuh.fit.chaper2test.servlet;

import jakarta.servlet.ServletConfig;
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
 * @since 8/12/2026
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if("admin".equals(username) && "123".equals(password)){
            resp.setStatus(HttpServletResponse.SC_OK);

            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write(
                    "<h1>Đăng nhập thành công</h1>" +
                            "<p>Xin chào " + username + "</p>"
            );
        }
        else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write("<h1>Đăng nhập thất bại</h1>" +
                    "<p>Sai username hoặc password</p>");
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws IOException {

        resp.setContentType("text/html;charset=UTF-8");

        resp.getWriter().write(
                "<h1>Login Servlet đang chạy</h1>"
        );
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}
