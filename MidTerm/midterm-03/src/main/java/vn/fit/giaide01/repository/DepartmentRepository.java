package vn.fit.giaide01.repository;

import vn.fit.giaide01.model.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentRepository {
    List<Department> findAll() throws SQLException;

    Department findById(int id) throws SQLException;

    boolean save(Department department) throws SQLException;
}
