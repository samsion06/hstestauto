package com.example.domain;

import lombok.Data;

import java.util.Date;
@Data
public class UserAliPayInfo {

    private Long id;
    private Long userId;
    private Long channelId;
    private String channelUserId;
    private String alipayUserId;
    private String alipayRealName;
    private String alipayAccount;
    private String nickName;
    private String headImg;
    private String identityCard;
    private Integer sex;
    private String province;
    private String city;
    private Integer status;
    private Integer isCertified;
    private Integer isDelete;
    private String isStudentCertified;
    private String userType;
    private String userStatus;
    private Date creatTime;
    private Date updateTime;


}
