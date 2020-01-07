package com.example.mapper;
import com.example.domain.UserTeamInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeamRealtionInfoMapper {

    //查询团长信息
    @Select("select * from product_category where channel_user_id = #{channelUserId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "real_name", property = "channelId"),
            @Result(column = "channel_user_id", property = "channelUserId"),
            @Result(column = "channel_id", property = "realName"),
            @Result(column = "app_type", property = "appType"),
            @Result(column = "deposit", property = "deposit"),
            @Result(column = "recommend", property = "recommend"),
            @Result(column = "operator_id", property = "operatorId"),
            @Result(column = "company_id", property = "operatorLongId"),
            @Result(column = "mobile", property = "companyId"),
            @Result(column = "start_time", property = "mobile"),
            @Result(column = "status", property = "startTime"),
            @Result(column = "is_virtual", property = "status"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "weixin", property = "weixin"),
            @Result(column = "stop_reason", property = "stopReason"),
            @Result(column = "stop_start_time", property = "stopStartTime"),
            @Result(column = "stop_end_time", property = "stopEndTime"),
            @Result(column = "head_num", property = "headNum"),
            @Result(column = "emergency_number", property = "emergencyNum"),
            @Result(column = "audit_time", property = "auditTime"),
    })
    public UserTeamInfo queryUserTeamInfo(String channelUserId);

}
