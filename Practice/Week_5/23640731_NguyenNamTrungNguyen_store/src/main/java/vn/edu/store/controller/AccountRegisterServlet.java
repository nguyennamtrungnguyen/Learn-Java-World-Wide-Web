package vn.edu.store.controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.edu.store.model.Account;
import vn.edu.store.service.AccountService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/registerform")
public class AccountRegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private AccountService accountService;

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/register.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            int day = Integer.parseInt(req.getParameter("day"));
            int month = Integer.parseInt(req.getParameter("month"));
            int year = Integer.parseInt(req.getParameter("year"));

            LocalDate dateOfBirth =
                    LocalDate.of(year, month, day);

            Account account = new Account(
                    firstname,
                    lastname,
                    email,
                    password,
                    dateOfBirth
            );

            boolean success =
                    accountService.createAccount(account);

            if (success) {

                List<Account> accounts =
                        accountService.getAllAccounts();

                req.setAttribute("accounts", accounts);

                req.getRequestDispatcher(
                        "/WEB-INF/views/account/list.jsp"
                ).forward(req, resp);

            } else {

                req.setAttribute(
                        "error",
                        "Đăng ký tài khoản thất bại!"
                );

                req.getRequestDispatcher(
                        "/register.jsp"
                ).forward(req, resp);
            }

        } catch (NumberFormatException e) {

            req.setAttribute(
                    "error",
                    "Ngày sinh không hợp lệ!"
            );

            req.getRequestDispatcher(
                    "/register.jsp"
            ).forward(req, resp);

        } catch (Exception e) {

            throw new ServletException(
                    "Lỗi khi đăng ký tài khoản",
                    e
            );
        }
    }
}