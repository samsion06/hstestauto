package com.example.mapper;

import com.example.domain.UserTeamInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeamRealtionInfo {

    //查询团长信息
    @Select("select * from product_category where channel_user_id = #{channelUserId}")
    public UserTeamInfo queryUserTeamInfo(String channelUserId);

}
