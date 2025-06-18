package com.evening.gui;

import com.evening.bean.Customer;
import com.evening.dao.CustomerDAO;
import com.evening.impl.CustomerInfoImp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * 按编号查询客户的功能类
 * 它首先弹出一个输入框，然后在一个新窗口中显示结果。
 * @author Evening
 */
public class FindOnID extends JFrame implements ActionListener {

    private JTable resultTable;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private JButton closeButton;
    private JPanel panel;

    // --- 数据访问对象 ---
    private CustomerDAO customerDAO = new CustomerInfoImp();

    public FindOnID() {
        // 弹出输入对话框获取ID
        String idStr = JOptionPane.showInputDialog(null, "请输入要查找的编号:", "输入对话框", JOptionPane.INFORMATION_MESSAGE);

        // 验证输入并查询
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr.trim());
                Customer customer = customerDAO.getCustomer(id);

                // 如果找到客户，则构建并显示结果窗口
                if (customer != null) {
                    // 初始化结果窗口的组件
                    initializeResultWindow(customer);
                } else {
                    JOptionPane.showMessageDialog(null, "未找到编号为 " + id + " 的客户。", "查询失败", JOptionPane.WARNING_MESSAGE);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "输入无效，编号必须是数字！", "输入错误", JOptionPane.ERROR_MESSAGE);
            }
        }
        // 如果用户点击取消或输入为空，则什么也不发生
    }

    /**
     * 初始化并显示结果窗口
     * @param customer 查询到的客户对象
     */
    private void initializeResultWindow(Customer customer) {
        panel = new JPanel(new BorderLayout());

        // 表格
        String[] columnNames = {"客户编号", "客户姓名", "年龄", "地址", "电话号码", "EMail"};
        tableModel = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        resultTable = new JTable(tableModel);
        scrollPane = new JScrollPane(resultTable);

        // 将唯一的查询结果添加到表格
        Vector<Object> rowData = new Vector<>();
        rowData.add(customer.getId());
        rowData.add(customer.getCustomName());
        rowData.add(customer.getAge());
        rowData.add(customer.getAddress());
        rowData.add(customer.getPhone());
        rowData.add(customer.getEmail());
        tableModel.addRow(rowData);

        panel.add(new JLabel("客户信息", JLabel.CENTER), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // 按钮
        closeButton = new JButton("关闭");
        closeButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // 设置窗口
        this.add(panel);
        this.setTitle("查询结果");
        this.setSize(700, 150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton) {
            this.dispose();
        }
    }
}