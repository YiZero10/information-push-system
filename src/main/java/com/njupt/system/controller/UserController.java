package com.njupt.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.njupt.system.model.Admin;
import com.njupt.system.model.SignInModel;
import com.njupt.system.model.User;
import com.njupt.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Elaine Huang
 * @date 2021/12/20 11:51 AM
 * @signature Do it while you can!
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String init(){return "Welcome!";}

    @RequestMapping("/register")
    public boolean register(@RequestBody User user){
        return userService.register(user);
    }

    @RequestMapping("/login")
    public String login(@RequestBody SignInModel signInModel){
        return userService.signIn(signInModel, signInModel.getPermission());
    }

    @RequestMapping("/getInfo")
    public JSONObject getInfo(User user, Admin admin){
        return (JSONObject) (user == null ? JSON.toJSON(admin) : JSON.toJSON(user));
    }

    public boolean addAdmin(Admin admin, Admin newAdmin){
        return userService.addAdmin(admin, newAdmin);
    }

    public boolean deleteAdmin(Admin admin, Integer adminId){
        return userService.deleteAdmin(admin, adminId);
    }

    public boolean resetAdminPwd(Admin admin, Integer adminId){
        return userService.resetAdminPassword(admin, adminId);
    }

    public List<Admin> getAdmins(Admin admin){
        return userService.getAdmins(admin);
    }

    public boolean deleteUser(Admin admin, Integer userId){
        return userService.deleteUser(admin, userId);
    }

    public boolean resetUserPwd(Admin admin, Integer userId){
        return userService.resetUserPassword(admin, userId);
    }

    public List<User> getUsers(Admin admin){
        return userService.getUsers(admin);
    }

    public boolean modifyPassword(Admin admin, User user, int type, String oldPwd, String newPwd){
        return type == 1 ? userService.modifyPassword(user, oldPwd, newPwd) : userService.modifyPassword(admin, oldPwd, newPwd);
    }
}
