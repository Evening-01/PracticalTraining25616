package com.evening.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * “关于我们”信息窗口
 * @author Evening
 */
public class AboutUs extends JFrame implements ActionListener {

    private JLabel label1, label2;
    private JButton closeButton;
    private JPanel panel;

    public AboutUs() {
        // 初始化组件
        label1 = new JLabel("版本号: CM 1.0");
        label1.setFont(new Font("宋体", Font.PLAIN, 16));
        label2 = new JLabel("联系方式: ccit@ccit.js.cn");
        label2.setFont(new Font("宋体", Font.PLAIN, 16));

        closeButton = new JButton("关闭");

        // 创建布局
        panel = new JPanel();
        panel.setLayout(null);

        label1.setBounds(80, 30, 200, 30);
        label2.setBounds(80, 70, 250, 30);
        closeButton.setBounds(130, 120, 80, 30);

        panel.add(label1);
        panel.add(label2);
        panel.add(closeButton);

        // 注册监听器
        closeButton.addActionListener(this);

        // 设置窗口
        this.add(panel);
        this.setTitle("关于我们"); //
        this.setSize(350, 220);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton) {
            this.dispose(); // 关闭窗口
        }
    }
}