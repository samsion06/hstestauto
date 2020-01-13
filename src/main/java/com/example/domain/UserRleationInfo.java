package com.example.domain;

import lombok.Data;

@Data
public class UserRleationInfo {

    private Long id;
    private Long channelId;
    private String channelUserId;
    private Integer appType;
    private String teamUserId;
    private Integer isDelete;

}
