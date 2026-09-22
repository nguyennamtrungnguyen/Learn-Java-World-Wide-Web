package vn.edu.store.controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.edu.store.service.AccountService;

import java.io.IOException;

@WebServlet("/accounts")
public class AccountServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private AccountService accountService;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(
                "accounts",
                accountService.getAllAccounts()
        );

        request.getRequestDispatcher(
                "/WEB-INF/views/account/list.jsp"
        ).forward(request, response);
    }
}