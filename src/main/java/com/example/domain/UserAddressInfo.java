package com.example.domain;

import lombok.Data;

import java.util.Date;
@Data
public class UserAddressInfo {
    //lowerCamelCase
    private Long id;
    private Long userId;
    private Long channelId;
    private String channelUserId;
    private String addressId;
    private String name;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String street;
    private String address;
    private Integer addressTag;
    private Integer isDefault;
    private Integer isDelete;
    private Date creatTime;
    private Date updateTime;

}
