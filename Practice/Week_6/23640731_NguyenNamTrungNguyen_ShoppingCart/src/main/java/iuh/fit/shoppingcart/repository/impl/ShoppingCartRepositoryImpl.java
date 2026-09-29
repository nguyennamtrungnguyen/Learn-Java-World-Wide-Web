package iuh.fit.shoppingcart.repository.impl;

import iuh.fit.shoppingcart.model.ShoppingCart;
import iuh.fit.shoppingcart.repository.ShoppingCartRepository;
import iuh.fit.shoppingcart.util.DBConnection;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {

    private static final String COLUMNS = "id, product_id, customer_name, quantity, created_at";

    private ShoppingCart map(ResultSet rs) throws SQLException {
        ShoppingCart cart = new ShoppingCart();
        cart.setId(rs.getInt("id"));
        cart.setProductId(rs.getInt("product_id"));
        cart.setCustomerName(rs.getString("customer_name"));
        cart.setQuantity(rs.getInt("quantity"));
        cart.setCreatedAt(rs.getTimestamp("created_at"));
        return cart;
    }

    @Override
    public List<ShoppingCart> findAll() {
        List<ShoppingCart> carts = new ArrayList<>();
        String sql = "SELECT " + COLUMNS + " FROM ShoppingCart";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) carts.add(map(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carts;
    }

    @Override
    public ShoppingCart findById(int id) {
        String sql = "SELECT " + COLUMNS + " FROM ShoppingCart WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(ShoppingCart cart) {
        // created_at do DB tu gan (CURRENT_TIMESTAMP)
        String sql = "INSERT INTO ShoppingCart (product_id, customer_name, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cart.getProductId());
            ps.setString(2, cart.getCustomerName());
            ps.setInt(3, cart.getQuantity());
            boolean ok = ps.executeUpdate() > 0;
            if (ok) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) cart.setId(keys.getInt(1));
                }
            }
            return ok;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ShoppingCart cart) {
        String sql = "UPDATE ShoppingCart SET product_id = ?, customer_name = ?, quantity = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cart.getProductId());
            ps.setString(2, cart.getCustomerName());
            ps.setInt(3, cart.getQuantity());
            ps.setInt(4, cart.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM ShoppingCart WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ShoppingCart> findByCustomerName(String customerName) {
        List<ShoppingCart> carts = new ArrayList<>();
        String sql = "SELECT " + COLUMNS + " FROM ShoppingCart WHERE customer_name = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customerName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) carts.add(map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carts;
    }

    @Override
    public int deleteByCustomerName(String customerName) {
        String sql = "DELETE FROM ShoppingCart WHERE customer_name = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customerName);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Map<Integer, Integer> sumQuantityByProduct() {
        Map<Integer, Integer> result = new HashMap<>();
        String sql = "SELECT product_id, SUM(quantity) AS total FROM ShoppingCart GROUP BY product_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) result.put(rs.getInt("product_id"), rs.getInt("total"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
