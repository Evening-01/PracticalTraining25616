package com.evening.dao;


import com.evening.bean.Customer;

import java.util.List;

/**
 * 客户信息处理接口
 * @author Evening
 */
public interface CustomerDAO {
    /**
     * 添加客户信息
     * @param c 包含新客户信息的Customer对象
     * @return 如果添加成功，返回true，否则返回false
     */
    boolean addCustomerInfo(Customer c);

    /**
     * 根据编号删除客户信息
     * @param id 要删除的客户的ID
     * @return 如果删除成功，返回true，否则返回false
     */
    boolean deleteCustomerInfo(int id);

    /**
     * 根据编号修改客户信息
     * @param id 要修改的客户的ID
     * @param c 包含更新后客户信息的Customer对象
     * @return 如果修改成功，返回true，否则返回false
     */
    boolean modifyCustomerInfo(int id, Customer c);

    /**
     * 根据编号查询客户信息
     * @param id 要查询的客户的ID
     * @return 如果找到，返回对应的Customer对象，否则返回null
     */
    Customer getCustomer(int id);

    /**
     * 根据姓名查询客户信息
     * @param name 要查询的客户姓名
     * @return 返回一个包含所有同名客户的List集合
     */
    List<Customer> getCustomer(String name);

    /**
     * 查询所有客户信息
     * @return 返回一个包含所有客户信息的List集合
     */
    List<Customer> selectAll();
}
