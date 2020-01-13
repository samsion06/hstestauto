package com.example.domain;

import lombok.Data;

@Data //lobok技术
public class UserTeamInfo {

    private Long id;
    private Integer channelId;
    private String channelUserId;
    private String realName;
    private Integer appType;
    private Long deposit;
    private String recommend;
    private String operatorId;
    private Long operatorLongId;
    private Long companyId;
    private String mobile;
    private Long startTime;
    private Integer status;
    private Integer isVirtual;
    private Integer gender;
    private String weixin;
    private String stopReason;
    private Long stopStartTime;
    private Long stopEndTime;
    private String headNum;
    private String emergencyNum;
    private Long auditTime;
    private Integer isDelete;
    private String auditorName;
    private Integer isShownCommission;
    private String licenseImg;
    private Integer source;
    private String operatorTel;
    private String companyName;


    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getOperatorTel() {
        return operatorTel;
    }

    public void setOperatorTel(String operatorTel) {
        this.operatorTel = operatorTel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public UserTeamInfo() {
    }

    public UserTeamInfo(Integer channelId, String channelUserId, String realName, Integer appType, Long deposit, String recommend, String operatorId, Long operatorLongId, Long companyId, String mobile, Long startTime, Integer status, Integer isVirtual, Integer gender, String weixin, String stopReason, Long stopStartTime, Long stopEndTime, String headNum, String emergencyNum, Long auditTime, String auditorName, Integer isShownCommission, String licenseImg, Integer source, String operatorTel, String companyName, Integer isDelete) {
        this.channelId = channelId;
        this.channelUserId = channelUserId;
        this.realName = realName;
        this.appType = appType;
        this.deposit = deposit;
        this.recommend = recommend;
        this.operatorId = operatorId;
        this.operatorLongId = operatorLongId;
        this.companyId = companyId;
        this.mobile = mobile;
        this.startTime = startTime;
        this.status = status;
        this.isVirtual = isVirtual;
        this.gender = gender;
        this.weixin = weixin;
        this.stopReason = stopReason;
        this.stopStartTime = stopStartTime;
        this.stopEndTime = stopEndTime;
        this.headNum = headNum;
        this.emergencyNum = emergencyNum;
        this.auditTime = auditTime;
        this.isDelete = isDelete;
        this.auditorName = auditorName;
        this.isShownCommission = isShownCommission;
        this.licenseImg = licenseImg;
        this.source = source;
        this.operatorTel = operatorTel;
        this.companyName = companyName;
    }
}