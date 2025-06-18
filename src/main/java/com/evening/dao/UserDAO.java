package com.evening.dao;

import com.evening.bean.User;

/**
 * 用户信息处理接口
 * @author Evening
 */
public interface UserDAO {

    /**
     * 判断用户是否存在
     * @param userName 用户名
     * @return 如果存在，返回true，否则返回false
     */
    boolean isExist(String userName);

    /**
     * 根据用户名和密码判断用户是否存在
     * @param userName 用户名
     * @param password 密码
     * @return 如果验证通过，返回true，否则返回false
     */
    boolean isExistUser(String userName, String password);

    /**
     * 添加新的用户
     * @param u 包含新用户信息的User对象
     * @return 如果添加成功，返回true，否则返回false
     */
    boolean insertUser(User u);

    /**
     * 修改用户密码
     * @param name 用户名
     * @param pass 新密码
     * @return 如果修改成功，返回true，否则返回false
     */
    boolean modifyUserPass(String name, String pass);
}