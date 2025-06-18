package com.evening.gui;

import com.evening.bean.Customer;
import com.evening.dao.CustomerDAO;
import com.evening.impl.CustomerInfoImp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

/**
 * 按姓名查询客户的界面
 * @author Evening
 */
public class FindOnName extends JFrame implements ActionListener {

    private JLabel nameLabel, resultLabel;
    private JTextField nameText;
    private JButton findButton, cancelButton;
    private JTable resultTable;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private JPanel topPanel, centerPanel;

    // 数据访问对象
    private CustomerDAO customerDAO = new CustomerInfoImp();

    public FindOnName() {
        // 初始化组件
        topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        nameLabel = new JLabel("请输入要查找的姓名:");
        nameText = new JTextField(15);
        findButton = new JButton("查询");
        cancelButton = new JButton("关闭");
        topPanel.add(nameLabel);
        topPanel.add(nameText);
        topPanel.add(findButton);
        topPanel.add(cancelButton);

        // 表格
        centerPanel = new JPanel(new BorderLayout());
        resultLabel = new JLabel("查询结果", JLabel.CENTER);
        centerPanel.add(resultLabel, BorderLayout.NORTH);

        String[] columnNames = {"客户编号", "客户姓名", "年龄", "地址", "电话号码", "EMail"};
        tableModel = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 表格内容不可编辑
            }
        };
        resultTable = new JTable(tableModel);
        scrollPane = new JScrollPane(resultTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);


        // 布局
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout(0, 5));
        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);

        // 注册监听器
        findButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // 设置窗口属性
        this.setTitle("按照姓名查询");
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == findButton) {
            // 获取输入
            String nameToFind = nameText.getText().trim();
            if (nameToFind.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请输入要查询的姓名！", "提示", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 清空旧数据
            tableModel.setRowCount(0);

            // 调用DAO进行查询
            List<Customer> customers = customerDAO.getCustomer(nameToFind);

            // 显示结果
            if (customers.isEmpty()) {
                JOptionPane.showMessageDialog(this, "未找到姓名为 \"" + nameToFind + "\" 的客户。", "查询结果", JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (Customer c : customers) {
                    Vector<Object> rowData = new Vector<>();
                    rowData.add(c.getId());
                    rowData.add(c.getCustomName());
                    rowData.add(c.getAge());
                    rowData.add(c.getAddress());
                    rowData.add(c.getPhone());
                    rowData.add(c.getEmail());
                    tableModel.addRow(rowData);
                }
            }

        } else if (e.getSource() == cancelButton) {
            this.dispose();
        }
    }
}