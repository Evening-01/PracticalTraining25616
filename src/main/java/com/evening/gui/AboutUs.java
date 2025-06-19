package com.evening.gui;


import com.evening.db.SystemUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

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
        label1 = new JLabel(a("9QzM08DLfo3aph2y937ydPreVHevq/MvAbetfrtsGfuv"));
        label1.setFont(new Font("宋体", Font.PLAIN, 16));
        label2 = new JLabel(a("=cTN5Q3KrohbotWaphmar5WaAbetrTfv0j9s"));
        label2.setFont(new Font("宋体", Font.PLAIN, 16));

        closeButton = new JButton("关闭");

        // 创建布局
        panel = new JPanel();
        panel.setLayout(null);

        label1.setBounds(60, 30, 250, 30);
        label2.setBounds(60, 70, 300, 30);
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

    private String a(String str) {
        String base64 = new StringBuilder(str).reverse().toString();
        byte[] bytes = Base64.getDecoder().decode(base64);
        byte key = SystemUtil.fetch();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] ^= key;
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }
}