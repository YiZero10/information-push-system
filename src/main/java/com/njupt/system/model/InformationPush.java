package com.njupt.system.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Elaine Huang
 * @date 2021/12/20
 */
@AllArgsConstructor
@NoArgsConstructor
public class InformationPush {
    private Integer id;

    private Integer informationId;

    private Integer userId;

    public InformationPush(Integer informationId, Integer userId) {
        this.informationId = informationId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}