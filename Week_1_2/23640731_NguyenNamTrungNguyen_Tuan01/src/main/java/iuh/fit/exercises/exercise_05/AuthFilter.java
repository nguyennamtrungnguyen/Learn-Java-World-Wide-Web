package iuh.fit.exercises.exercise_05;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
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

@WebFilter("/section5/secure/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest, // HTTP, SMTP, FTP, ...
            ServletResponse servletResponse, // 1997
            FilterChain filterChain
    ) throws IOException, ServletException {

        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;

        var session = request.getSession();

        boolean isLogged = session != null && session.getAttribute("username") != null;

        if (isLogged) filterChain.doFilter(servletRequest, servletResponse);
        else response.sendRedirect(request.getContextPath() + "/section5/LoginPage.jsp");
    }
}