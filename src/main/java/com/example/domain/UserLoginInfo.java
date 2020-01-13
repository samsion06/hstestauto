package com.example.domain;

import lombok.Data;

import java.util.Date;
@Data
public class UserLoginInfo {
    private Long id;
    private Long userId;
    private Integer channelId;
    private String channelUserId;
    private String loginName;
    private String loginPwd;
    private String loginSalt;
    private Integer isDelete;
    private Date creatTime;
    private Date updateTime;


}
