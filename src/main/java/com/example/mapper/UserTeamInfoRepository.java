//package com.example.mapper;
//
//import com.hs.user.base.entity.UserTeamInfoEntity;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
///**
//@description
//
//@package ${PACKAGE_NAME}
//@auth Admin
//@since 2019 12 2019/12/30
//*/
//
//public interface UserTeamInfoRepository {
//    int insert(UserTeamInfoEntity record);
//
//    int insertSelective(UserTeamInfoEntity record);
//
//    UserTeamInfoEntity selectUserTeamInfo(UserTeamInfoEntity record);
//
//    List<UserTeamInfoEntity> selectUserTeamInfoBatch(@Param("channelUserIdList") List<String> channelUserIdList, @Param("appType") Integer appType, @Param("channelId") Integer channelId);
//
//    int updateUserTeamInfo(UserTeamInfoEntity record);
//
//    int deleteUserTeamInfo(UserTeamInfoEntity record);
//}