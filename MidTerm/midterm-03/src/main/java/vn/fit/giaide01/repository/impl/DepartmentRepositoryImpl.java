package vn.fit.giaide01.repository.impl;

import jakarta.enterprise.context.ApplicationScoped;
import vn.fit.giaide01.model.Department;
import vn.fit.giaide01.repository.DepartmentRepository;
import vn.fit.giaide01.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DepartmentRepositoryImpl implements DepartmentRepository {
    private Department mapRow(ResultSet rs) throws SQLException {
        return new Department(
                rs.getInt("id"),
                rs.getString("name")
        );
    }

    @Override
    public List<Department> findAll() throws SQLException{
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM Departments";

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
    public Department findById(int id) throws SQLException{
        String sql = "SELECT * FROM Departments where id = ?";

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
    public boolean save(Department department) throws SQLException{
        String sql = "INSERT INTO Departments (name) values (?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ){
            ps.setString(1,department.getName());
            boolean ok = ps.executeUpdate() > 0;
            if(ok) {

                try(ResultSet keys = ps.getGeneratedKeys()){
                    if(keys.next()){
                        department.setId(keys.getInt(1));
                    }
                }
            }
            return ok;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
