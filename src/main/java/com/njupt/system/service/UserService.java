package com.njupt.system.service;

import com.njupt.system.model.Admin;
import com.njupt.system.model.SignInModel;
import com.njupt.system.model.User;

import java.util.List;

/**
 * @author Elaine Huang
 * @date 2021/12/20 11:52 AM
 * @signature Do it while you can!
 */
public interface UserService {
    /**
     * 注册
     * @param user
     * @return register success?
     */
    boolean register(User user);

    /**
     * 登陆
     * @return 签发 token
     */
    String signIn(SignInModel signInModel, int permission);

    /**
     * 获取用户信息
     * @param user
     * @return
     */
    User getUserInfo(User user);

    /**
     * 管理员获取用户列表
     * @param admin
     * @return
     */
    List<User> getUsers(Admin admin);

    /**
     * 系统管理员获取管理员列表
     * @param admin
     * @return
     */
    List<Admin> getAdmins(Admin admin);

    /**
     * 添加管理员
     * @param admin
     * @param addAdmin
     * @return
     */
    boolean addAdmin(Admin admin, Admin addAdmin);

    /**
     * 删除管理员
     * @param admin
     * @param adminId
     * @return
     */
    boolean deleteAdmin(Admin admin, Integer adminId);

    /**
     * 删除用户
     * @param admin
     * @param userId
     * @return
     */
    boolean deleteUser(Admin admin, Integer userId);

    /**
     * 验证密码
     * @param user
     * @param password
     * @return
     */
    boolean checkPassword(User user, String password);

    /**
     * 修改密码
     * @param user
     * @param password
     * @return
     */
    boolean modifyPassword(User user, String password);

    /**
     * 管理员重置密码
     * @param admin
     * @param userId
     * @return
     */
    boolean resetPassword(Admin admin, Integer userId);

}
