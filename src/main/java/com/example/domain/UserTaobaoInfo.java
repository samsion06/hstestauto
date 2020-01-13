package com.example.domain;

import lombok.Data;

import java.util.Date;
@Data
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


}
