package com.njupt.system.service.Impl;

import com.njupt.system.mapper.InformationMapper;
import com.njupt.system.model.Admin;
import com.njupt.system.model.Information;
import com.njupt.system.model.User;
import com.njupt.system.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Elaine Huang
 * @date 2021/12/20 2:29 PM
 * @signature Do it while you can!
 */
@Component
public class InformationServiceImpl implements InformationService {
    @Autowired
    private InformationMapper informationMapper;

    @Override
    public List<Information> getMyInformations(User user,Integer type) {

        return null;
    }

    @Override
    public boolean submitInformation(Admin admin, Information information) {
        return false;
    }

    @Override
    public boolean auditInformation(Admin admin, Integer informationId, boolean isPass) {
        return false;
    }

    @Override
    public boolean deleteInformation(Admin admin, Integer informationId) {
        return false;
    }

}
