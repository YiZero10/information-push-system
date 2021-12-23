package com.njupt.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.njupt.system.config.CheckAdmin;
import com.njupt.system.config.TokenInterceptor;
import com.njupt.system.enums.Permission;
import com.njupt.system.model.Admin;
import com.njupt.system.model.SignInModel;
import com.njupt.system.model.User;
import com.njupt.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
    public String login(@RequestBody SignInModel signInModel, HttpServletResponse response){
        String token = userService.signIn(signInModel, signInModel.getPermission());
        response.addHeader("Authorization", token);
        return token;
    }

    @RequestMapping("/getInfo")
    public JSONObject getInfo(@RequestParam("pms") Integer type){
        if (type <= Permission.COMMON.getCode())
            return (JSONObject) JSON.toJSON(TokenInterceptor.adminHolder.get());
        return (JSONObject) JSON.toJSON(TokenInterceptor.userHolder.get());
    }

    @CheckAdmin
    @RequestMapping("/admin/add")
    public boolean addAdmin(Admin admin, @RequestBody Admin newAdmin){
        return userService.addAdmin(admin, newAdmin);
    }

    @CheckAdmin
    @RequestMapping("/admin/del")
    public boolean deleteAdmin(Admin admin, @RequestParam("adminId") Integer adminId){
        return userService.deleteAdmin(admin, adminId);
    }

    @CheckAdmin
    @RequestMapping("/admin/pwd/reset")
    public boolean resetAdminPwd(Admin admin, @RequestParam("adminId") Integer adminId){
        return userService.resetAdminPassword(admin, adminId);
    }

    @CheckAdmin
    @RequestMapping("/admin/get/admins")
    public List<Admin> getAdmins(Admin admin){
        return userService.getAdmins(admin);
    }

    @CheckAdmin
    @RequestMapping("/user/del")
    public boolean deleteUser(Admin admin, @RequestParam("userId") Integer userId){
        return userService.deleteUser(admin, userId);
    }

    @CheckAdmin
    @RequestMapping("/user/pwd/reset")
    public boolean resetUserPwd(Admin admin, @RequestParam("userId") Integer userId){
        return userService.resetUserPassword(admin, userId);
    }

    @CheckAdmin
    @RequestMapping("/admin/get/users")
    public List<User> getUsers(Admin admin){
        return userService.getUsers(admin);
    }

    @CheckAdmin
    @RequestMapping("/admin/pwd/modify")
    public boolean modifyAdminPassword(Admin admin, @RequestParam("old") String oldPwd, @RequestParam("new") String newPwd){
        return userService.modifyPassword(admin, oldPwd, newPwd);
    }

    @RequestMapping("/user/pwd/modify")
    public boolean modifyUsrPassword(User user, @RequestParam("old") String oldPwd, @RequestParam("new") String newPwd){
        return userService.modifyPassword(user, oldPwd, newPwd);
    }
}
