package com.njupt.system.service.Impl;

import com.njupt.system.enums.InformationType;
import com.njupt.system.enums.Permission;
import com.njupt.system.enums.Status;
import com.njupt.system.enums.VisibleRange;
import com.njupt.system.exception.CustomError;
import com.njupt.system.exception.LocalRuntimeException;
import com.njupt.system.mapper.AdminMapper;
import com.njupt.system.mapper.InformationMapper;
import com.njupt.system.mapper.InformationPushMapper;
import com.njupt.system.mapper.UserMapper;
import com.njupt.system.model.Admin;
import com.njupt.system.model.Information;
import com.njupt.system.model.User;
import com.njupt.system.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Elaine Huang
 * @date 2021/12/20 2:29 PM
 * @signature Do it while you can!
 */
@Component
public class InformationServiceImpl implements InformationService {
    @Autowired
    private InformationMapper informationMapper;
    @Autowired
    private InformationPushMapper informationPushMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Information> getMyInformation(User user,Integer type) {
        if (type == 0)
            //选出所有通过的公共信息
            return informationMapper.selectByTypeAndStatus(InformationType.PUBLIC_INFORMATION.getCode(), Status.PASS_AUDIT.getCode());
        else{
            //选出公共可见的&指定推送的信息
            List<Information> results = informationPushMapper.selectMyInformation(user,type);
            results.addAll(informationMapper.selectByTypeAndStatus(type, Status.PASS_AUDIT.getCode()));
            return results;
        }
    }

    @Override
    public List<Information> getInformation(Admin admin, Integer type) {
        if (isRoot(admin)){
            //返回该类型下所有的信息
            return informationMapper.selectAll().stream().filter(information -> information.getType().equals(type)).collect(Collectors.toList());
        }else if (isDeptAdmin(admin)){
            //部门所有的管理员
            List<Integer> myAdmins = adminMapper.selectMyAdmins(admin);
            //选出这个部门所有管理员发布的该类型的信息
            return informationMapper.selectAll().stream().filter(information -> information.getType().equals(type) &&
                    myAdmins.contains(information.getAdminId())).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public boolean submitInformation(Admin admin, Information information, List<String> objects) {
        if (information.getType().equals(InformationType.PUBLIC_INFORMATION.getCode()))
            informationMapper.insert(information);
        else if (information.getVisibleRange().equals(VisibleRange.PUBLIC.getCode())){
            informationMapper.insert(information);
        }else if (information.getVisibleRange().equals(VisibleRange.SPECIFIED_CLASS.getCode())){
            int id = informationMapper.insert(information);
            for (String item:objects) {
                List<Integer> insertObjects = userMapper.selectByClassId(item);
                informationPushMapper.insertSpecifiedClass(id, insertObjects);
            }
        }else if (information.getVisibleRange().equals(VisibleRange.SPECIFIED_PERSON.getCode())){
            int id = informationMapper.insert(information);
            for (String item:objects) {
                List<Integer> ids = userMapper.selectIdsByStudentId(item);
                informationPushMapper.insertSpecifiedClass(id, ids);
            }
        }
        return true;
    }

    @Override
    public boolean auditInformation(Admin admin, Integer informationId, boolean isPass) {
        Information information = informationMapper.selectByPrimaryKey(informationId);
        if (information == null) throw new LocalRuntimeException(CustomError.INFORMATION_NOT_EXIT);
        information.setStatus(isPass ? Status.PASS_AUDIT.getCode() : Status.FAIL_AUDIT.getCode());
        if (isRoot(admin))
            return informationMapper.updateByPrimaryKey(information) == 1;
        else if (isDeptAdmin(admin)){
            List<Integer> myAdmins = adminMapper.selectMyAdmins(admin);
            if (!myAdmins.contains(information.getAdminId()))
                throw new LocalRuntimeException(CustomError.NOT_PERMISSION);
            return informationMapper.updateByPrimaryKey(information) == 1;
        }
        return true;
    }

    @Override
    public boolean deleteInformation(Admin admin, Integer informationId) {
        Information information = informationMapper.selectByPrimaryKey(informationId);
        if (information == null) throw new LocalRuntimeException(CustomError.INFORMATION_NOT_EXIT);
        if (isRoot(admin))
            return informationMapper.deleteByPrimaryKey(informationId) == 1;
        else if (isDeptAdmin(admin)){
            List<Integer> myAdmins = adminMapper.selectMyAdmins(admin);
            if (!myAdmins.contains(information.getAdminId()))
                throw new LocalRuntimeException(CustomError.NOT_PERMISSION);
            return informationMapper.deleteByPrimaryKey(informationId) == 1;
        }
        return true;
    }

    private boolean isRoot(Admin admin){
        return admin.getType().equals(Permission.ROOT.getCode());
    }

    private boolean isDeptAdmin(Admin admin){
        return admin.getType().equals(Permission.DEPARTMENT.getCode());
    }
}
