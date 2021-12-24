package com.njupt.system.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Elaine Huang
 * @date 2021/12/20
 */
@AllArgsConstructor
@NoArgsConstructor
public class Information {
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图
     */
    private String coverImg;

    /**
     * 信息类型
     */
    private Integer type;

    /**
     * 发布者id
     */
    private Integer adminId;

    /**
     * 审核状态
     */
    private Integer status;

    /**
     * 可见范围
     */
    private Integer visibleRange;

    /**
     * 发布时间
     */
    private Date releaseTime;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 内容
     */
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg == null ? null : coverImg.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId ;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVisibleRange() {
        return visibleRange;
    }

    public void setVisibleRange(Integer visibleRange) {
        this.visibleRange = visibleRange;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}