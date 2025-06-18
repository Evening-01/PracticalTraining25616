package com.evening.gui;

import com.evening.dao.UserDAO;
import com.evening.impl.UserImp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 修改用户密码的界面
 * @author Evening
 */
public class UserPass extends JFrame implements ActionListener {

    private JLabel titleLabel, userLabel, userValueLabel, newPassLabel, confirmPassLabel;
    private JPasswordField newPassField, confirmPassField;
    private JButton okButton, cancelButton;
    private JPanel panel;

    private UserDAO userDAO = new UserImp();
    private String currentUserName; // 存储当前用户名

    /**
     * 构造函数
     * @param currentUserName 当前登录的用户名
     */
    public UserPass(String currentUserName) {
        this.currentUserName = currentUserName;

        // 初始化组件
        titleLabel = new JLabel("修改当前用户的密码");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 18));
        userLabel = new JLabel("用户名:");
        // 这个Label用于显示不可修改的用户名
        userValueLabel = new JLabel(this.currentUserName);
        userValueLabel.setFont(new Font("Arial", Font.BOLD, 14));
        newPassLabel = new JLabel("新密码:");
        confirmPassLabel = new JLabel("密码确认:");

        newPassField = new JPasswordField(20);
        confirmPassField = new JPasswordField(20);

        okButton = new JButton("确定");
        cancelButton = new JButton("取消");

        // 布局
        panel = new JPanel();
        panel.setLayout(null);

        titleLabel.setBounds(100, 20, 250, 30);
        userLabel.setBounds(80, 70, 80, 25);
        userValueLabel.setBounds(170, 70, 150, 25);
        newPassLabel.setBounds(80, 110, 80, 25);
        newPassField.setBounds(170, 110, 150, 25);
        confirmPassLabel.setBounds(80, 150, 80, 25);
        confirmPassField.setBounds(170, 150, 150, 25);
        okButton.setBounds(100, 200, 80, 30);
        cancelButton.setBounds(220, 200, 80, 30);

        panel.add(titleLabel);
        panel.add(userLabel);
        panel.add(userValueLabel);
        panel.add(newPassLabel);
        panel.add(newPassField);
        panel.add(confirmPassLabel);
        panel.add(confirmPassField);
        panel.add(okButton);
        panel.add(cancelButton);

        // 注册监听器
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // 设置窗口
        this.add(panel);
        this.setTitle("修改用户密码");
        this.setSize(420, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            // 获取输入
            String newPass = new String(newPassField.getPassword()).trim();
            String confirmPass = new String(confirmPassField.getPassword()).trim();

            // 输入验证
            if (newPass.isEmpty() || confirmPass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "新密码和确认密码不能为空！", "输入错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(this, "两次输入的新密码不一致！", "输入错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 调用DAO更新密码
            boolean success = userDAO.modifyUserPass(this.currentUserName, newPass);

            // 反馈结果
            if (success) {
                JOptionPane.showMessageDialog(this, "密码修改成功！");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "密码修改失败，可能发生了数据库错误。", "操作失败", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == cancelButton) {
            this.dispose();
        }
    }
}
