package com.evening.gui;

import com.evening.dao.UserDAO;
import com.evening.impl.UserImp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 用户登录界面
 * @author Evening
 */
public class Login extends JFrame implements ActionListener {

    // 定义组件
    private JLabel titleLabel, userLabel, passLabel;
    private JTextField userTextField;
    private JPasswordField passField;
    private JButton okButton, cancelButton;
    private JPanel panel;

    // 引入用户数据操作实现类
    private UserDAO userDao = new UserImp();

    public Login() {
        // 初始化组件
        titleLabel = new JLabel("欢迎进入客户信息管理系统");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 22)); // 设置字体

        userLabel = new JLabel("用户名:");
        passLabel = new JLabel("密码:");

        userTextField = new JTextField(20);
        passField = new JPasswordField(20);

        okButton = new JButton("确定");
        cancelButton = new JButton("退出");

        // 设置布局和添加组件
        panel = new JPanel();
        panel.setLayout(null); // 使用绝对布局

        // 设置各组件的位置和大小
        titleLabel.setBounds(60, 20, 300, 30);
        userLabel.setBounds(80, 70, 60, 25);
        userTextField.setBounds(150, 70, 150, 25);
        passLabel.setBounds(80, 110, 60, 25);
        passField.setBounds(150, 110, 150, 25);
        okButton.setBounds(100, 160, 80, 30);
        cancelButton.setBounds(210, 160, 80, 30);

        // 将组件添加到面板
        panel.add(titleLabel);
        panel.add(userLabel);
        panel.add(userTextField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(okButton);
        panel.add(cancelButton);

        this.add(panel); // 将面板添加到窗口

        // 注册事件监听器
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // 设置窗口属性
        this.setTitle("用户登录");
        this.setSize(400, 250);
        this.setLocationRelativeTo(null); // 窗口居中显示
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口时退出程序
        this.setResizable(false); // 禁止调整窗口大小
        this.setVisible(true); // 显示窗口
    }

    /**
     * 事件处理
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // 判断事件来源
        if (e.getSource() == okButton) {
            // 获取用户输入
            String userName = userTextField.getText().trim();
            String password = new String(passField.getPassword()).trim();

            // 输入验证
            if (userName.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "用户名或密码不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                return; // 中断方法执行
            }

            // 调用DAO方法进行登录验证
            if (userDao.isExistUser(userName, password)) {
                // 登录成功
                JOptionPane.showMessageDialog(this, "登录成功！");
                this.dispose(); // 关闭当前登录窗口
                new MainFrame(userName); // 打开主界面，并将用户名传递过去
            } else {
                // 登录失败
                JOptionPane.showMessageDialog(this, "用户名或密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == cancelButton) {
            // 点击退出按钮
            System.exit(0);
        }
    }

    /**
     * 程序主入口
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Login();
    }
}