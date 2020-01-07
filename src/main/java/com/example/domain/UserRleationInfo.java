package com.example.domain;

public class UserRleationInfo {

    private Long id;
    private Long channelId;
    private String channelUserId;
    private Integer appType;
    private String teamUserId;
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

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getTeamUserId() {
        return teamUserId;
    }

    public void setTeamUserId(String teamUserId) {
        this.teamUserId = teamUserId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "UserRleationInfo{" +
                "id=" + id +
                ", channelId=" + channelId +
                ", channelUserId='" + channelUserId + '\'' +
                ", appType=" + appType +
                ", teamUserId='" + teamUserId + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
