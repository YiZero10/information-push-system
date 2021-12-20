package com.njupt.system.service;

import com.njupt.system.model.Admin;
import com.njupt.system.model.Information;
import com.njupt.system.model.User;

import java.util.List;

/**
 * @author Elaine Huang
 * @date 2021/12/20 2:19 PM
 * @signature Do it while you can!
 */
public interface InformationService {

    /**
     * 获取消息列表
     * @param user
     * @return
     */
    List<Information> getMyInformations(User user);

    /**
     * 提交信息
     * @param admin
     * @param information
     * @return
     */
    boolean submitInformation(Admin admin, Information information);

    /**
     * 审核信息
     * @param admin
     * @param informationId
     * @param isPass
     * @return
     */
    boolean auditInformation(Admin admin, Integer informationId, boolean isPass);

    /**
     * 修改信息
     * @param admin
     * @param informationId
     * @return
     */
    boolean deleteInformation(Admin admin, Integer informationId);
}
