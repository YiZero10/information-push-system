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
     * 获取自己的消息列表
     * @param user
     * @return
     */
    List<Information> getMyInformation(User user,Integer type);


    /**
     * 管理员获取消息
     * @param admin
     * @param type
     * @return
     */
    List<Information> getInformation(Admin admin, Integer type);

    /**
     * 获取待审核的消息
     * @param admin
     * @param type
     * @return
     */
    List<Information> getWaitAuditInfos(Admin admin, Integer type);

    /**
     * 获取消息的详情
     * @param id
     * @return
     */
    Information getInformationDetail(Integer id);

    /**
     * 提交信息
     * @param admin
     * @param information
     * @return
     */
    boolean submitInformation(Admin admin, Information information, List<String> objects);

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
