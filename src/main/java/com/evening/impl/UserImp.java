package com.evening.impl;

import com.evening.bean.User;
import com.evening.dao.UserDAO;
import com.evening.db.DBCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImp implements UserDAO {
    private final DBCon db = new DBCon(); // 创建DBCon对象，用于获取数据库连接

    // isExist 方法的实现
    @Override
    public boolean isExist(String userName) {
        // 使用 try-with-resources 语句确保资源被自动关闭
        try (Connection con = db.getConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM users WHERE userName = ?")) {

            pstmt.setString(1, userName); // 设置查询参数
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // 如果rs.next()为true，说明查询到了记录，用户存在
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // 如果发生异常或未查询到，返回false
    }

    // isExistUser 方法的实现
    @Override
    public boolean isExistUser(String userName, String password) {
        String sql = "SELECT * FROM users WHERE userName = ? AND password = ?";
        try (Connection con = db.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, userName);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // 如果查询到匹配的用户名和密码，返回true
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // insertUser 方法的实现
    @Override
    public boolean insertUser(User u) {
        String sql = "INSERT INTO users (userName, password) VALUES (?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, u.getUserName());
            pstmt.setString(2, u.getPassword());

            int result = pstmt.executeUpdate(); // executeUpdate() 返回受影响的行数
            return result > 0; // 如果行数大于0，说明插入成功

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // modifyUserPass 方法的实现
    @Override
    public boolean modifyUserPass(String name, String pass) {
        String sql = "UPDATE users SET password = ? WHERE userName = ?";
        try (Connection con = db.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, pass);   // 第一个 '?' 对应新密码
            pstmt.setString(2, name);   // 第二个 '?' 对应用户名

            int result = pstmt.executeUpdate();
            return result > 0; // 如果受影响行数大于0，说明更新成功

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
