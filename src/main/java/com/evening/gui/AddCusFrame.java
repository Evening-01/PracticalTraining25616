package com.evening.gui;

import com.evening.bean.Customer;
import com.evening.dao.CustomerDAO;
import com.evening.impl.CustomerInfoImp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 添加客户信息的界面
 * @author Evening
 */
public class AddCusFrame extends JFrame implements ActionListener {

    private JLabel nameLabel, passLabel, ageLabel, phoneLabel, addressLabel, emailLabel;
    private JTextField nameText, ageText, phoneText, emailText;
    private JPasswordField passText; // 密码框
    private JTextField addressText;
    private JButton addButton, cancelButton;
    private JPanel panel;

    private CustomerDAO customerDAO = new CustomerInfoImp();
    private CusManFrame parentFrame; // 持有父窗口的引用

    /**
     * 构造函数
     * @param parentFrame 父窗口 (CusManFrame) 的引用
     */
    public AddCusFrame(CusManFrame parentFrame) {
        this.parentFrame = parentFrame;

        // 初始化组件
        nameLabel = new JLabel("客户姓名:");
        passLabel = new JLabel("客户密码:");
        ageLabel = new JLabel("客户年龄:");
        phoneLabel = new JLabel("客户电话:");
        addressLabel = new JLabel("客户地址:");
        emailLabel = new JLabel("客户邮箱:");

        nameText = new JTextField(20);
        passText = new JPasswordField(20);
        ageText = new JTextField(20);
        phoneText = new JTextField(20);
        addressText = new JTextField(40);
        emailText = new JTextField(40);

        addButton = new JButton("添加");
        cancelButton = new JButton("关闭");

        // 布局
        panel = new JPanel();
        panel.setLayout(null); // 使用绝对布局

        // 设置两列布局
        nameLabel.setBounds(50, 30, 80, 25);
        nameText.setBounds(130, 30, 150, 25);
        passLabel.setBounds(300, 30, 80, 25);
        passText.setBounds(380, 30, 150, 25);

        ageLabel.setBounds(50, 70, 80, 25);
        ageText.setBounds(130, 70, 150, 25);
        phoneLabel.setBounds(300, 70, 80, 25);
        phoneText.setBounds(380, 70, 150, 25);

        addressLabel.setBounds(50, 110, 80, 25);
        addressText.setBounds(130, 110, 400, 25);

        emailLabel.setBounds(50, 150, 80, 25);
        emailText.setBounds(130, 150, 400, 25);

        addButton.setBounds(180, 200, 80, 30);
        cancelButton.setBounds(320, 200, 80, 30);

        // 添加组件到面板
        panel.add(nameLabel);
        panel.add(nameText);
        panel.add(passLabel);
        panel.add(passText);
        panel.add(ageLabel);
        panel.add(ageText);
        panel.add(phoneLabel);
        panel.add(phoneText);
        panel.add(addressLabel);
        panel.add(addressText);
        panel.add(emailLabel);
        panel.add(emailText);
        panel.add(addButton);
        panel.add(cancelButton);

        // 注册监听器
        addButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // 设置窗口
        this.add(panel);
        this.setTitle("客户信息管理界面"); //
        this.setSize(600, 300);
        this.setLocationRelativeTo(parentFrame); // 相对父窗口居中
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // 获取用户输入
            String name = nameText.getText().trim();
            String password = new String(passText.getPassword()).trim();
            String ageStr = ageText.getText().trim();
            String phone = phoneText.getText().trim();
            String address = addressText.getText().trim();
            String email = emailText.getText().trim();

            // 输入验证
            if (name.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "客户姓名和密码不能为空！", "输入错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int age;
            try {
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "年龄必须是一个有效的数字！", "输入错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 封装成Customer对象
            Customer customer = new Customer();
            customer.setCustomName(name);
            customer.setCustomPassword(password);
            customer.setAge(age);
            customer.setPhone(phone);
            customer.setAddress(address);
            customer.setEmail(email);

            // 调用DAO进行数据库操作
            boolean success = customerDAO.addCustomerInfo(customer);

            // 根据结果给出反馈
            if (success) {
                JOptionPane.showMessageDialog(this, "客户添加成功！");
                parentFrame.refreshTableData(); // 回调父窗口的刷新方法
                this.dispose(); // 关闭当前窗口
            } else {
                JOptionPane.showMessageDialog(this, "添加失败，可能发生了数据库错误。", "操作失败", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == cancelButton) {
            this.dispose(); // 关闭窗口
        }
    }
}