package vn.fit.giaide01.repository.impl;

import jakarta.enterprise.context.ApplicationScoped;
import vn.fit.giaide01.model.Employee;
import vn.fit.giaide01.repository.EmployeeRepository;
import vn.fit.giaide01.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private Employee mapRow(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("salary"),
                rs.getInt("departmentId")
        );
    }

    @Override
    public List<Employee>findAll() throws SQLException{
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employees";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                list.add(mapRow(rs));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return list;
    }
    @Override
    public Employee findById(int id) throws SQLException{
        String sql = "SELECT * FROM Employees where id = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return mapRow(rs);
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return null;
    }
    @Override
    public boolean save(Employee employee) throws SQLException{
        String sql = "INSERT INTO Employees (name, salary, departmentId) values (?,?,?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ){
            ps.setString(1,employee.getName());
            ps.setDouble(2,employee.getSalary());
            ps.setInt(3, employee.getDepartmentId());

            boolean ok = ps.executeUpdate() > 0;
            if(ok) {

                try(ResultSet keys = ps.getGeneratedKeys()){
                    if(keys.next()){
                        employee.setId(keys.getInt(1));
                    }
                }
            }
            return ok;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    @Override
    public boolean findByDepartmentAndSalaryRange(int departmentId, BigDecimal minSalary, BigDecimal maxSalary){
        return false;
    }

}
