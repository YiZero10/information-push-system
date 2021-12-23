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
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean register(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return userMapper.insert(user) == 1;
    }

    @Override
    public String signIn(SignInModel signInModel, int permission) {

        if (signInModel.getUserId() == null || signInModel.getPassword() == null){
            throw new LocalRuntimeException(CustomError.CONTENT_NULL);
        }

        JSONObject object = (JSONObject) (permission <= Permission.COMMON.getCode() ? JSON.toJSON(adminMapper.selectByJobId(signInModel.getUserId()))
                        : JSON.toJSON(userMapper.selectByTel(signInModel.getUserId())));

        if (object == null) throw new LocalRuntimeException(CustomError.USER_NOT_EXIT);

        if (!BCrypt.checkpw(signInModel.getPassword(), object.getString("password"))){
            throw new LocalRuntimeException(CustomError.PASSWORD_ERROR);
        }else {
            return jwtAuthService.GeneratorToken(signInModel.getUserId(), 1);
        }
    }

    @Override
    public User getUserInfo(User user) {
        return null;
    }

    @Override
    public List<User> getUsers(Admin admin) {
        if (checkAdminPermission(admin)) return userMapper.selectAll();
        return null;
    }

    @Override
    public List<Admin> getAdmins(Admin admin) {
        if (Permission.ROOT.getCode().equals(admin.getType())) return adminMapper.selectAll();
        if (Permission.DEPARTMENT.getCode().equals(admin.getDepartment())) return adminMapper.selectDepartment(admin.getDepartment());
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addAdmin(Admin admin, Admin addAdmin) {
        //不是系统管理员&&同部门管理员
        if (!checkRootPermission(admin) && !checkDepartmentPermission(admin, addAdmin))
            throw new LocalRuntimeException(CustomError.NOT_PERMISSION);
        Admin object = adminMapper.selectByJobId(addAdmin.getJobId());
        if (object != null) throw new LocalRuntimeException(CustomError.DUPLICATE_INSERT);
        addAdmin.setPassword(BCrypt.hashpw(admin.getPassword(),BCrypt.gensalt()));
        adminMapper.insert(addAdmin);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteAdmin(Admin admin, Integer adminId) {
        if (adminId.equals(admin.getId())) throw new LocalRuntimeException(CustomError.DELETE_SELF);
        Admin object = adminMapper.selectByPrimaryKey(adminId);
        if (object == null) throw new LocalRuntimeException(CustomError.USER_NOT_EXIT);
        //不是系统管理员&&同部门管理员
        if (!checkRootPermission(admin) && !checkDepartmentPermission(admin, object))
            throw new LocalRuntimeException(CustomError.NOT_PERMISSION);
        return adminMapper.deleteByPrimaryKey(adminId) == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUser(Admin admin, Integer userId) {
        //不是系统管理员
        if (!checkRootPermission(admin)) throw new LocalRuntimeException(CustomError.NOT_PERMISSION);
        return userMapper.deleteByPrimaryKey(userId) == 1;
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return BCrypt.checkpw(password, user.getPassword());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean modifyPassword(User user, String oldPwd, String newPwd) {
        if (!BCrypt.checkpw(oldPwd, user.getPassword())) throw new LocalRuntimeException(CustomError.PASSWORD_ERROR);
        user.setPassword(BCrypt.hashpw(newPwd,BCrypt.gensalt()));
        userMapper.updateByPrimaryKey(user);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean modifyPassword(Admin admin, String oldPwd, String newPwd){
        if (!BCrypt.checkpw(oldPwd, admin.getPassword())) throw new LocalRuntimeException(CustomError.PASSWORD_ERROR);
        admin.setPassword(BCrypt.hashpw(newPwd,BCrypt.gensalt()));
        adminMapper.updateByPrimaryKey(admin);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean resetUserPassword(Admin admin, Integer userId) {
        //不是系统管理员
        if (!checkRootPermission(admin)) throw new LocalRuntimeException(CustomError.NOT_PERMISSION);

        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) throw new LocalRuntimeException(CustomError.USER_NOT_EXIT);

        user.setPassword(BCrypt.hashpw(user.getStudentId(),BCrypt.gensalt()));

        userMapper.updateByPrimaryKey(user);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean resetAdminPassword(Admin admin, Integer adminId) {
        Admin object = adminMapper.selectByPrimaryKey(adminId);
        if (object == null) throw new LocalRuntimeException(CustomError.USER_NOT_EXIT);

        //不是系统管理员&&同部门管理员
        if (!checkRootPermission(admin) && !checkDepartmentPermission(admin, object))
            throw new LocalRuntimeException(CustomError.NOT_PERMISSION);

        object.setPassword(BCrypt.hashpw(object.getJobId(),BCrypt.gensalt()));

        adminMapper.updateByPrimaryKey(object);
        return true;
    }

    private boolean checkRootPermission(Admin admin){
        return Permission.ROOT.getCode().equals(admin.getType());
    }

    private boolean checkDepartmentPermission(Admin admin, Admin commonAdmin){
        return admin.getType() < commonAdmin.getType() && admin.getDepartment().equals(commonAdmin.getDepartment());
    }

    private boolean checkAdminPermission(Admin admin){
        return admin.getType() < Permission.COMMON.getCode();
    }
}
