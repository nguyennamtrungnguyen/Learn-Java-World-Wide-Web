package iuh.fit.ontapgiuaky.repository.impl;

import iuh.fit.ontapgiuaky.model.Product;
import iuh.fit.ontapgiuaky.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/30/2026
 */
public class ProductRepositoryImpl {

    private Product mapRow(ResultSet rs) throws  SQLException{
        return new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getString("description"),
                rs.getString("image_url")
        );
    }
    public List<Product> findAll(){
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()){
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    public Product findById(int id){
        String sql = "SELECT * FROM Product where id = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Product newProduct){
        return
    }

    public boolean delete(int id){
        String sql = ""
    }
}
