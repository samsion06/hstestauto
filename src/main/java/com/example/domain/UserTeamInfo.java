package com.example.domain;

public class UserTeamInfo {

    private Long id;
    private Integer channelId;
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
    private String auditorName;
    private Integer isShownCommission;
    private String licenseImg;

//    public UserTeamInfo() {
//    }

    public UserTeamInfo(Integer channelId, String channelUserId, String realName, Integer appType, Double deposit, String recommend, String operatorId, Long operatorLongId, Long companyId, String mobile, Long startTime, Integer status, Integer isVirtual, Integer gender, String weixin, String stopReason, Long stopStartTime, Long stopEndTime, String headNum, String emergencyNum, Long auditTime, Integer isDelete, String auditorName, Integer isShownCommission, String licenseImg) {
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
    }

    public String getLicenseImg() {
        return licenseImg;
    }

    public void setLicenseImg(String licenseImg) {
        this.licenseImg = licenseImg;
    }

    public Integer getIsShownCommission() {
        return isShownCommission;
    }

    public void setIsShownCommission(Integer isShownCommission) {
        this.isShownCommission = isShownCommission;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
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
                ", auditorName='" + auditorName + '\'' +
                '}';
    }
}
