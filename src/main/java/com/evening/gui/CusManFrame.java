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
 * 客户信息管理主界面
 * @author Evening
 */
public class CusManFrame extends JFrame implements ActionListener {

    private JTable cusTable;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private JButton addButton, modifyButton, deleteButton, exitButton;
    private JPanel buttonPanel;

    // 数据访问对象
    private CustomerDAO customerDAO = new CustomerInfoImp();

    public CusManFrame() {
        // 初始化组件
        // 定义表格列名
        String[] columnNames = {"客户编号", "客户姓名", "年龄", "地址", "电话号码", "EMail"};
        tableModel = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        cusTable = new JTable(tableModel); // 使用模型创建表格
        scrollPane = new JScrollPane(cusTable); // 将表格放入滚动面板

        // 创建按钮
        addButton = new JButton("添加");
        modifyButton = new JButton("修改");
        deleteButton = new JButton("删除");
        exitButton = new JButton("退出");

        // 布局
        buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER); // 表格在中间
        contentPane.add(buttonPanel, BorderLayout.SOUTH); // 按钮在下方

        // 注册监听器-
        addButton.addActionListener(this);
        modifyButton.addActionListener(this);
        deleteButton.addActionListener(this);
        exitButton.addActionListener(this);

        // 加载初始数据
        refreshTableData();

        // 设置窗口属性
        this.setTitle("客户信息管理");
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 只关闭当前窗口
        this.setVisible(true);
    }

    /**
     * 从数据库加载数据并刷新表格
     */
    public void refreshTableData() {
        // 先清空表格的所有行
        tableModel.setRowCount(0);

        // 从数据库查询所有客户信息
        List<Customer> customers = customerDAO.selectAll();
        for (Customer c : customers) {
            Vector<Object> rowData = new Vector<>();
            rowData.add(c.getId());
            rowData.add(c.getCustomName());
            rowData.add(c.getAge());
            rowData.add(c.getAddress());
            rowData.add(c.getPhone());
            rowData.add(c.getEmail());
            tableModel.addRow(rowData); // 将一行数据添加到模型中
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // 打开添加窗口，并把this传过去，方便添加成功后回调刷新
            new AddCusFrame(this);

        } else if (e.getSource() == modifyButton) {
            int selectedRow = cusTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "请先选择要修改的客户！", "提示", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // 从表格模型中获取选中行的客户ID (第0列)
            int customerId = (int) tableModel.getValueAt(selectedRow, 0);
            // 打开修改窗口，并传入customerId
            new ModifyCusFrame(this, customerId);

        } else if (e.getSource() == deleteButton) {
            int selectedRow = cusTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "请先选择要删除的客户！", "提示", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // 弹出确认对话框
            int choice = JOptionPane.showConfirmDialog(this, "确定要删除该客户吗？", "确认删除", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                int customerId = (int) tableModel.getValueAt(selectedRow, 0);
                boolean success = customerDAO.deleteCustomerInfo(customerId);
                if (success) {
                    JOptionPane.showMessageDialog(this, "删除成功！");
                    refreshTableData(); // 刷新表格
                } else {
                    JOptionPane.showMessageDialog(this, "删除失败！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == exitButton) {
            this.dispose(); // 关闭当前窗口
        }
    }
}