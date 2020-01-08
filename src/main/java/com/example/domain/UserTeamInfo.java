package com.example.domain;

public class UserTeamInfo {

    private Long id;
    private Long channelId;
    private String channelUserId;
    private String realName;
    private Integer appType;
    private Double deposit;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public Long getOperatorLongId() {
        return operatorLongId;
    }

    public void setOperatorLongId(Long operatorLongId) {
        this.operatorLongId = operatorLongId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getStopReason() {
        return stopReason;
    }

    public void setStopReason(String stopReason) {
        this.stopReason = stopReason;
    }

    public Long getStopStartTime() {
        return stopStartTime;
    }

    public void setStopStartTime(Long stopStartTime) {
        this.stopStartTime = stopStartTime;
    }

    public Long getStopEndTime() {
        return stopEndTime;
    }

    public void setStopEndTime(Long stopEndTime) {
        this.stopEndTime = stopEndTime;
    }

    public String getHeadNum() {
        return headNum;
    }

    public void setHeadNum(String headNum) {
        this.headNum = headNum;
    }

    public String getEmergencyNum() {
        return emergencyNum;
    }

    public void setEmergencyNum(String emergencyNum) {
        this.emergencyNum = emergencyNum;
    }

    public Long getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Long auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "UserTeamInfo{" +
                "id=" + id +
                ", channelId=" + channelId +
                ", channelUserId='" + channelUserId + '\'' +
                ", realName='" + realName + '\'' +
                ", appType=" + appType +
                ", deposit=" + deposit +
                ", recommend='" + recommend + '\'' +
                ", operatorId='" + operatorId + '\'' +
                ", operatorLongId=" + operatorLongId +
                ", companyId=" + companyId +
                ", mobile='" + mobile + '\'' +
                ", startTime=" + startTime +
                ", status=" + status +
                ", isVirtual=" + isVirtual +
                ", gender=" + gender +
                ", weixin='" + weixin + '\'' +
                ", stopReason='" + stopReason + '\'' +
                ", stopStartTime=" + stopStartTime +
                ", stopEndTime=" + stopEndTime +
                ", headNum='" + headNum + '\'' +
                ", emergencyNum='" + emergencyNum + '\'' +
                ", auditTime=" + auditTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
