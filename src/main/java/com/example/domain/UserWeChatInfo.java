package com.example.domain;

import lombok.Data;

import java.util.Date;
@Data
public class UserWeChatInfo {

    private Long id;
    private String appId;
    private Long userId;
    private String channelUserId;
    private Long channelId;
    private String openId;
    private String unionId;
    private String wxNo;
    private String nickName;
    private Integer sex;
    private String province;
    private String city;
    private String country;
    private String headImg;
    private Integer isDelete;
    private Date creatTime;
    private Date updateTime;

}
