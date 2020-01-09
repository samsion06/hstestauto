package com.example.domain;

import java.util.Date;

public class UserTaobaoInfo {

    private Long id;
    private Long userId;
    private Long channelId;
    private String channelUserId;
    private Integer relationId;
    private Integer specialId;
    private Long companyId;
    private  Long tbAccountId;
    private String tbAccount;
    private Integer isDelete;
    private Date createTime;
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelUserId() {
        return channelUserId;
    }

    public void setChannelUserId(String channelUserId) {
        this.channelUserId = channelUserId;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public Integer getSpecialId() {
        return specialId;
    }

    public void setSpecialId(Integer specialId) {
        this.specialId = specialId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getTbAccountId() {
        return tbAccountId;
    }

    public void setTbAccountId(Long tbAccountId) {
        this.tbAccountId = tbAccountId;
    }

    public String getTbAccount() {
        return tbAccount;
    }

    public void setTbAccount(String tbAccount) {
        this.tbAccount = tbAccount;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserTaobaoInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", channelId=" + channelId +
                ", relationId=" + relationId +
                ", specialId=" + specialId +
                ", companyId=" + companyId +
                ", tbAccountId=" + tbAccountId +
                ", tbAccount='" + tbAccount + '\'' +
                ", isDelete=" + isDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
