package com.evening.db;


import java.io.File;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
    public Connection getConnection() {
        Connection con = null;
        try {
            // 加载驱动
            Class.forName("org.sqlite.JDBC");

             /*
               从资源路径(resources目录)获取数据库文件的URL
               注意: getResource("/") 代表资源根目录, "customer_management.db"就在其下
              */
            URL dbUrl = DBCon.class.getResource("/customer_management.db");

            // 检查文件是否存在
            if (dbUrl == null) {
                System.err.println("错误：在 resources 目录下找不到数据库文件: customer_management.db");
                // 给出更详细的提示
                System.err.println("请确认你已经创建了 'src/main/resources' 目录，并将 'customer_management.db' 放入其中。");
                return null;
            }

            // 使用文件的绝对路径建立连接
            File dbFile = new File(dbUrl.toURI());
            con = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());

        } catch (ClassNotFoundException e) {
            System.err.println("驱动找不到");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("数据库连接不成功！请检查文件路径或数据库文件是否损坏。");
            e.printStackTrace();
        } catch (Exception e) {
            // 捕获其他可能的异常，如URISyntaxException
            System.err.println("加载数据库文件时发生未知错误。");
            e.printStackTrace();
        }
        return con;
    }
}