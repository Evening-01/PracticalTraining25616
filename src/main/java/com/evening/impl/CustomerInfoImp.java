package com.evening.impl;

import com.evening.bean.Customer;
import com.evening.dao.CustomerDAO;
import com.evening.db.DBCon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户信息处理业务类
 * 实现了 CustomerDAO 接口
 * @author Evening
 */

public class CustomerInfoImp implements CustomerDAO {
    private DBCon db = new DBCon(); // 创建DBCon对象，用于获取数据库连接

    @Override
    public boolean addCustomerInfo(Customer c) {
        String sql = "INSERT INTO customers (customName, customPassword, age, address, phone, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, c.getCustomName());
            pstmt.setString(2, c.getCustomPassword());
            pstmt.setInt(3, c.getAge());
            pstmt.setString(4, c.getAddress());
            pstmt.setString(5, c.getPhone());
            pstmt.setString(6, c.getEmail());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCustomerInfo(int id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modifyCustomerInfo(int id, Customer c) {
        String sql = "UPDATE customers SET customName = ?, customPassword = ?, age = ?, address = ?, phone = ?, email = ? WHERE id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, c.getCustomName());
            pstmt.setString(2, c.getCustomPassword());
            pstmt.setInt(3, c.getAge());
            pstmt.setString(4, c.getAddress());
            pstmt.setString(5, c.getPhone());
            pstmt.setString(6, c.getEmail());
            pstmt.setInt(7, id);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Customer getCustomer(int id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Customer c = new Customer();
                    c.setId(rs.getInt("id"));
                    c.setCustomName(rs.getString("customName"));
                    c.setCustomPassword(rs.getString("customPassword"));
                    c.setAge(rs.getInt("age"));
                    c.setAddress(rs.getString("address"));
                    c.setPhone(rs.getString("phone"));
                    c.setEmail(rs.getString("email"));
                    return c;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // 如果没有找到或发生异常，返回null
    }

    @Override
    public List<Customer> getCustomer(String name) {
        List<Customer> customerList = new ArrayList<>();
        // 使用 LIKE 和 % 实现模糊查询
        String sql = "SELECT * FROM customers WHERE customName LIKE ?";
        try (Connection con = db.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, "%" + name + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Customer c = new Customer();
                    c.setId(rs.getInt("id"));
                    c.setCustomName(rs.getString("customName"));
                    c.setCustomPassword(rs.getString("customPassword"));
                    c.setAge(rs.getInt("age"));
                    c.setAddress(rs.getString("address"));
                    c.setPhone(rs.getString("phone"));
                    c.setEmail(rs.getString("email"));
                    customerList.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList; // 返回列表，如果没有找到则列表为空
    }

    @Override
    public List<Customer> selectAll() {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        // 这里使用 Statement 即可，因为没有参数
        try (Connection con = db.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setCustomName(rs.getString("customName"));
                c.setCustomPassword(rs.getString("customPassword"));
                c.setAge(rs.getInt("age"));
                c.setAddress(rs.getString("address"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                customerList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }
}
