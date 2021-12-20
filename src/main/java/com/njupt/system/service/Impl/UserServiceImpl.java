package com.njupt.system.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.njupt.system.enums.Permission;
import com.njupt.system.exception.CustomError;
import com.njupt.system.exception.LocalRuntimeException;
import com.njupt.system.mapper.AdminMapper;
import com.njupt.system.mapper.UserMapper;
import com.njupt.system.model.Admin;
import com.njupt.system.model.SignInModel;
import com.njupt.system.model.User;
import com.njupt.system.service.JwtAuthService;
import com.njupt.system.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Elaine Huang
 * @date 2021/12/20 3:30 PM
 * @signature Do it while you can!
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtAuthService jwtAuthService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean register(User user) {
        return false;
    }

    @Override
    public String signIn(SignInModel signInModel, int permission) {
        if (signInModel.getUserId() == null){
            throw new LocalRuntimeException(CustomError.ACCOUNT_NULL);
        }
        if (signInModel.getPassword() == null){
            throw new LocalRuntimeException(CustomError.PASSWORD_NULL);
        }

        JSONObject object = permission <= Permission.COMMON.getCode() ? JSONObject.parseObject(adminMapper.selectByJobId(signInModel.getUserId()).toString())
                : JSONObject.parseObject(userMapper.selectByTel(Integer.valueOf(signInModel.getUserId())).toString());

        if (!BCrypt.checkpw(object.getString("password"), signInModel.getPassword())){
            throw new LocalRuntimeException(CustomError.PASSWORD_ERROR);
        }else {
            return jwtAuthService.GeneratorToken(object.getInteger("id"), 1);
        }
    }

    @Override
    public User getUserInfo(User user) {
        return null;
    }

    @Override
    public List<User> getUsers(Admin admin) {
        return null;
    }

    @Override
    public List<Admin> getAdmins(Admin admin) {
        return null;
    }

    @Override
    public boolean addAdmin(Admin admin, Admin addAdmin) {
        return false;
    }

    @Override
    public boolean deleteAdmin(Admin admin, Integer adminId) {
        return false;
    }

    @Override
    public boolean deleteUser(Admin admin, Integer userId) {
        return false;
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return false;
    }

    @Override
    public boolean modifyPassword(User user, String password) {
        return false;
    }

    @Override
    public boolean resetPassword(Admin admin, Integer userId) {
        return false;
    }
}
