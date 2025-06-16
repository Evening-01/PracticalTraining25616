package com.evening.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接类 (SQLite版本)
 * @author Evening
 */
public class DBCon {
    /**
     * 获取数据库连接
     * @return Connection对象或null
     */

    public Connection getConnection() {
        Connection con = null;
        try {
            // 1. 加载SQLite JDBC驱动
            Class.forName("org.sqlite.JDBC");

            // 2. 建立数据库连接
            // URL格式为 "jdbc:sqlite:数据库文件名"
            // 这将在项目根目录下创建或连接一个名为 customer_management.db 的数据库文件
            con = DriverManager.getConnection("jdbc:sqlite:customer_management.db");

        } catch (ClassNotFoundException e) {
            // 驱动未找到异常
            System.err.println("驱动找不到");
            e.printStackTrace();
        } catch (SQLException e) {
            // SQLite连接异常
            System.err.println("数据库连接不成功！");
            e.printStackTrace();
        }
        return con;
    }
}
