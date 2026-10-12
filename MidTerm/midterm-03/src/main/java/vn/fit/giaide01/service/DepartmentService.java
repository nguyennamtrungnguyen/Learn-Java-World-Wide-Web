package vn.fit.giaide01.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import vn.fit.giaide01.model.Department;
import vn.fit.giaide01.repository.DepartmentRepository;
import vn.fit.giaide01.repository.impl.DepartmentRepositoryImpl;


import java.sql.*;
import java.util.List;

@ApplicationScoped
public class DepartmentService {

    @Inject
    private DepartmentRepository departmentRepository;

    public List<Department> findAll() throws SQLException {
        return departmentRepository.findAll();
    }
    public Department findById(int id) throws SQLException{
        return departmentRepository.findById(id);
    }
    public boolean save(Department department) throws SQLException{
       return departmentRepository.save(department);
    }
}
