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
            @Result(column = "id", property = "categoryId"),
            @Result(column = "real_name", property = "categoryName"),
            @Result(column = "channel_user_id", property = "categoryType"),
            @Result(column = "channel_id", property = "categoryType"),
            @Result(column = "app_type", property = "categoryType"),
            @Result(column = "deposit", property = "categoryType"),
            @Result(column = "recommend", property = "categoryType"),
            @Result(column = "operator_id", property = "categoryType"),
            @Result(column = "company_id", property = "categoryType"),
            @Result(column = "mobile", property = "categoryType"),
            @Result(column = "start_time", property = "categoryType"),
            @Result(column = "status", property = "categoryType"),
            @Result(column = "is_virtual", property = "categoryType"),
            @Result(column = "gender", property = "categoryType"),
            @Result(column = "weixin", property = "categoryType"),
            @Result(column = "stop_reason", property = "categoryType"),
            @Result(column = "stop_start_time", property = "categoryType"),
            @Result(column = "stop_end_time", property = "categoryType"),
            @Result(column = "head_num", property = "categoryType"),
            @Result(column = "emergency_number", property = "categoryType"),
            @Result(column = "audit_time", property = "categoryType"),
    })
    public UserTeamInfo queryUserTeamInfo(String channelUserId);

}
