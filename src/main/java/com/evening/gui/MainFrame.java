package com.evening.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * 应用程序主界面
 * @author Evening
 */

public class MainFrame extends JFrame implements ActionListener {

    // 存储当前登录的用户名
    private String currentUserName;

    // 菜单组件
    private JMenuBar menuBar;
    private JMenu menuCustomer, menuUser, menuHelp, menuExit;
    private JMenuItem miFindByName, miFindByID, miManage, miRegister, miChangePass, miAbout, miExit;

    // 工具栏组件
    private JToolBar toolBar;
    private JButton btnManage, btnFindByName, btnFindByID, btnRegister, btnChangePass, btnAbout, btnExit;

    // 状态栏组件
    private JLabel statusBar;

    public MainFrame(String userName) {
        this.currentUserName = userName;

        // 创建菜单栏
        menuBar = new JMenuBar();
        // 创建主菜单
        menuCustomer = new JMenu("客户信息管理(C)");
        menuCustomer.setMnemonic('C'); // 设置助记键 Alt+C
        menuUser = new JMenu("用户信息管理(U)");
        menuUser.setMnemonic('U');
        menuHelp = new JMenu("帮助(H)");
        menuHelp.setMnemonic('H');
        menuExit = new JMenu("退出(X)");
        menuExit.setMnemonic('X');

        // 创建菜单项
        miManage = new JMenuItem("客户管理");
        miFindByName = new JMenuItem("按姓名查找");
        miFindByID = new JMenuItem("按编号查找");
        miRegister = new JMenuItem("用户注册");
        miChangePass = new JMenuItem("修改密码");
        miAbout = new JMenuItem("关于我们");
        miExit = new JMenuItem("退出系统");

        // 创建工具栏
        toolBar = new JToolBar();
        btnManage = new JButton("客户管理");
        btnFindByName = new JButton("按姓名查找");
        btnFindByID = new JButton("按编号查找");
        btnRegister = new JButton("用户注册");
        btnChangePass = new JButton("修改密码");
        btnAbout = new JButton("关于我们");
        btnExit = new JButton("退出系统");

        // 创建背景面板
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                URL imageUrl = MainFrame.class.getResource("/picture/bj.jpg");

                if (imageUrl != null) {
                    ImageIcon icon = new ImageIcon(imageUrl);
                    // 绘制图片以填充整个面板
                    g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
                } else {
                    // 如果图片未找到，给出一个提示，避免程序出错
                    System.err.println("错误：在 resources 目录下找不到背景图片");
                    g.setColor(Color.RED);
                    g.drawString("背景图片加载失败!", 20, 20);
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout()); // 设置布局以容纳其他内容

        // 创建状态栏
        statusBar = new JLabel(" 欢迎您：" + this.currentUserName, JLabel.LEFT);
        statusBar.setBorder(BorderFactory.createEtchedBorder());

        // 组装菜单
        menuCustomer.add(miManage);
        menuCustomer.addSeparator(); // 添加分隔线
        menuCustomer.add(miFindByName);
        menuCustomer.add(miFindByID);
        menuUser.add(miRegister);
        menuUser.add(miChangePass);
        menuHelp.add(miAbout);
        menuExit.add(miExit);
        menuBar.add(menuCustomer);
        menuBar.add(menuUser);
        menuBar.add(menuHelp);
        menuBar.add(menuExit);
        this.setJMenuBar(menuBar); // 将菜单栏设置到窗口

        // 组装工具栏
        toolBar.add(btnManage);
        toolBar.add(btnFindByName);
        toolBar.add(btnFindByID);
        toolBar.addSeparator();
        toolBar.add(btnRegister);
        toolBar.add(btnChangePass);
        toolBar.addSeparator();
        toolBar.add(btnAbout);
        toolBar.add(btnExit);
        toolBar.setFloatable(false); // 设置工具栏不可拖动

        // 注册事件监听
        // 为所有可交互组件添加监听器，并设置ActionCommand以区分它们
        miManage.setActionCommand("manage");
        miFindByName.setActionCommand("findByName");
        miFindByID.setActionCommand("findByID");
        miRegister.setActionCommand("register");
        miChangePass.setActionCommand("changePass");
        miAbout.setActionCommand("about");
        miExit.setActionCommand("exit");

        btnManage.setActionCommand("manage");
        btnFindByName.setActionCommand("findByName");
        btnFindByID.setActionCommand("findByID");
        btnRegister.setActionCommand("register");
        btnChangePass.setActionCommand("changePass");
        btnAbout.setActionCommand("about");
        btnExit.setActionCommand("exit");

        ActionListener listener = this; // 当前对象作为监听器
        miManage.addActionListener(listener);
        miFindByName.addActionListener(listener);
        miFindByID.addActionListener(listener);
        miRegister.addActionListener(listener);
        miChangePass.addActionListener(listener);
        miAbout.addActionListener(listener);
        miExit.addActionListener(listener);

        btnManage.addActionListener(listener);
        btnFindByName.addActionListener(listener);
        btnFindByID.addActionListener(listener);
        btnRegister.addActionListener(listener);
        btnChangePass.addActionListener(listener);
        btnAbout.addActionListener(listener);
        btnExit.addActionListener(listener);

        // 组装窗口
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(backgroundPanel, BorderLayout.CENTER); // 使用带背景的面板
        contentPane.add(statusBar, BorderLayout.SOUTH);

        // 设置窗口属性
        this.setTitle("客户信息管理系统");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // 获取动作命令

        // 根据不同的命令执行不同的操作
        switch (command) {
            case "manage":
                new CusManFrame();
                break;
            case "findByName":
                new FindOnName();
                break;

            case "findByID":
                // 该功能通常是一个输入对话框，不是窗口！！！
                new FindOnID();
                break;

            case "register":
                new UserRegister();
                break;

            case "changePass":
                new UserPass(this.currentUserName); // 将当前用户名传入
                break;

            case "about":
                new AboutUs();
                break;

            case "exit":
                System.exit(0);
                break;
        }
    }
}