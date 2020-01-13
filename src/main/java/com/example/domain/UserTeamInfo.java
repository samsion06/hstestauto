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

}