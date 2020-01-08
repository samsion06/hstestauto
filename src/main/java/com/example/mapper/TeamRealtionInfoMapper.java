package com.example.mapper;
import com.example.domain.UserRleationInfo;
import com.example.domain.UserTeamAdress;
import com.example.domain.UserTeamInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeamRealtionInfoMapper {

    //查询团长信息
    @Select("select * from user_team_info where channel_user_id = #{channelUserId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "channel_user_id", property = "channelUserId"),
            @Result(column = "channel_id", property = "channelId"),
            @Result(column = "real_name", property = "realName"),
            @Result(column = "app_type", property = "appType"),
            @Result(column = "deposit", property = "deposit"),
            @Result(column = "recommend", property = "recommend"),
            @Result(column = "operator_id", property = "operatorId"),
            @Result(column = "company_id", property = "operatorLongId"),
            @Result(column = "mobile", property = "companyId"),
            @Result(column = "start_time", property = "mobile"),
            @Result(column = "status", property = "startTime"),
            @Result(column = "is_virtual", property = "status"),
            @Result(column = "is_virtual", property = "isVirtual"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "weixin", property = "weixin"),
            @Result(column = "stop_reason", property = "stopReason"),
            @Result(column = "stop_start_time", property = "stopStartTime"),
            @Result(column = "stop_end_time", property = "stopEndTime"),
            @Result(column = "head_num", property = "headNum"),
            @Result(column = "emergency_number", property = "emergencyNum"),
            @Result(column = "audit_time", property = "auditTime"),
            @Result(column = "is_delete", property = "isDelete"),
            @Result(column = "auditor_name", property = "auditorName"),
            @Result(column = "is_show_commission", property = "isShownCommission"),
            @Result(column = "license_img", property = "licenseImg"),
            @Result(column = "source", property = "source"),
            @Result(column = "operator_tel", property = "operatorTel"),
            @Result(column = "company_name", property = "companyName"),
    })
    public UserTeamInfo queryUserTeamInfo(String channelUserId);

    @Select("select * from user_team_info where channel_user_id = #{channelUserId}")
    //查询团长地址
    public UserTeamAdress queryUserTeamAddressInfo(String channelUserId);

    //查询团长关系
    @Select("select * from user_team_relation where channel_user_id = #{channelUserId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "channel_id", property = "channelId"),
            @Result(column = "channel_user_id", property = "channelUserId"),
            @Result(column = "app_type", property = "appType"),
            @Result(column = "team_user_id", property = "teamUserId"),
            @Result(column = "is_delete", property = "isDelete"),
    })
    public UserRleationInfo queryUserRleationInfo(String channelUserId);

}
