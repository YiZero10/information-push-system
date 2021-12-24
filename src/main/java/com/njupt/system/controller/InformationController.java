package com.njupt.system.controller;

import com.njupt.system.config.CheckAdmin;
import com.njupt.system.model.Admin;
import com.njupt.system.model.Information;
import com.njupt.system.model.User;
import com.njupt.system.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Elaine Huang
 * @date 2021/12/20 2:19 PM
 * @signature Do it while you can!
 */
@RestController
@RequestMapping("/info")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @RequestMapping("/users")
    public List<Information> getMyInfos(User user, @RequestParam(value = "type",defaultValue = "0",required = false) Integer type){
        return informationService.getMyInformation(user, type);
    }

    @CheckAdmin
    @RequestMapping("/admins")
    public List<Information> getInfos(Admin admin,@RequestParam(value = "type",defaultValue = "0",required = false) Integer type){
        return informationService.getInformation(admin,type);
    }

    @CheckAdmin
    @RequestMapping("/add")
    public boolean addInformation(Admin admin, Information information, List<String> objects){
        return informationService.submitInformation(admin, information, objects);
    }

    @CheckAdmin
    @RequestMapping("/audit")
    public boolean auditInformation(Admin admin, @RequestParam("infoId") Integer informationId, @RequestParam("isPass") boolean isPass){
        return informationService.auditInformation(admin, informationId, isPass);
    }

    @CheckAdmin
    @RequestMapping("/del")
    public boolean deleteInformation(Admin admin, @RequestParam("infoId") Integer informationId){
        return informationService.deleteInformation(admin, informationId);
    }

}
