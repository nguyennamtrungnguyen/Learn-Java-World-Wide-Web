package iuh.fit.midterm01.controller;

import iuh.fit.midterm01.entity.Department;
import iuh.fit.midterm01.service.DepartmentService;
import iuh.fit.midterm01.service.impl.DepartmentServiceImpl;
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
 * @since 7/9/2026
 */

@WebServlet("/departments")
public class DepartmentController extends HttpServlet {

    public DepartmentService departmentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        departmentService = new DepartmentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");

        if(name != null) {
            req.setAttribute("departments", departmentService.findByName(name));
        }
        else {
            req.setAttribute("departments", departmentService.findAll());
        }

        req.getRequestDispatcher("/WEB-INF/department-list.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if(action != null){
            switch (action){
                case "DELETE" -> {
                    Long id = Long.parseLong(req.getParameter("id"));
                    departmentService.delete(id);
                    resp.sendRedirect("departments");// Cập nhập lại trang chủ
                }

                case "ADD" -> {
                    String name = req.getParameter("name");

                    Department department = new Department(null, name, null);

                    departmentService.save(department);
                }
            }
        }
    }



}
