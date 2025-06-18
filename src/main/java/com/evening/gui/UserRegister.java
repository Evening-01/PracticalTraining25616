package com.evening.gui;

import com.evening.bean.User;
import com.evening.dao.UserDAO;
import com.evening.impl.UserImp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 用户注册界面
 * @author Evening
 */
public class UserRegister extends JFrame implements ActionListener {

    private JLabel titleLabel, userLabel, pass1Label, pass2Label;
    private JTextField nameText;
    private JPasswordField pass1Field, pass2Field;
    private JButton okButton, cancelButton;
    private JPanel panel;

    // 数据访问对象
    private UserDAO userDAO = new UserImp();

    public UserRegister() {
        // 初始化组件
        titleLabel = new JLabel("注册新用户");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 18));
        userLabel = new JLabel("用户名:");
        pass1Label = new JLabel("密码:");
        pass2Label = new JLabel("密码确认:");

        nameText = new JTextField(20);
        pass1Field = new JPasswordField(20);
        pass2Field = new JPasswordField(20);

        okButton = new JButton("确定");
        cancelButton = new JButton("取消");

        // 布局
        panel = new JPanel();
        panel.setLayout(null);

        titleLabel.setBounds(140, 20, 150, 30);
        userLabel.setBounds(80, 70, 80, 25);
        nameText.setBounds(170, 70, 150, 25);
        pass1Label.setBounds(80, 110, 80, 25);
        pass1Field.setBounds(170, 110, 150, 25);
        pass2Label.setBounds(80, 150, 80, 25);
        pass2Field.setBounds(170, 150, 150, 25);
        okButton.setBounds(100, 200, 80, 30);
        cancelButton.setBounds(220, 200, 80, 30);

        panel.add(titleLabel);
        panel.add(userLabel);
        panel.add(nameText);
        panel.add(pass1Label);
        panel.add(pass1Field);
        panel.add(pass2Label);
        panel.add(pass2Field);
        panel.add(okButton);
        panel.add(cancelButton);

        // 注册监听器
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // 设置窗口
        this.add(panel);
        this.setTitle("用户注册");
        this.setSize(420, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            // 获取输入
            String userName = nameText.getText().trim();
            String pass1 = new String(pass1Field.getPassword()).trim();
            String pass2 = new String(pass2Field.getPassword()).trim();

            // 多重输入验证
            if (userName.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "用户名和密码不能为空！", "输入错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!pass1.equals(pass2)) {
                JOptionPane.showMessageDialog(this, "两次输入的密码不一致！", "输入错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 检查用户名是否已存在
            if (userDAO.isExist(userName)) {
                JOptionPane.showMessageDialog(this, "该用户名已被占用，请更换一个！", "注册失败", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 3. 封装对象并调用DAO
            User user = new User();
            user.setUserName(userName);
            user.setPassword(pass1);

            boolean success = userDAO.insertUser(user);

            // 4. 反馈结果
            if (success) {
                JOptionPane.showMessageDialog(this, "用户注册成功！现在可以使用新账户登录。");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "注册失败，可能发生了数据库错误。", "操作失败", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == cancelButton) {
            this.dispose();
        }
    }
}