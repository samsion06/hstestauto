package com.example.domain;

import lombok.Data;

import java.util.Date;
@Data
public class UserBaseInfo {

    private Long id;
    private Long userId;
    private Integer channelId;
    private String channelUserId;
    private String nickName;
    private String realName;
    private String mobileAreaCode;
    private String mobile;
    private Integer sex;
    private String  birthday;
    private String headImg;
    private String identityCard;
    private Integer userRole;
    private Integer source;
    private String registerRecommendUserId;
    private Integer userStatus;
    private Integer isDelete;
    private Date creatTime;
    private Date updateTime;

}
