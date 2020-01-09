package com.example.utils;
import com.example.domain.*;
import com.example.mapper.TeamRealtionInfoMapper;
import com.example.mapper.UserBaseInfoMapper;
import org.testng.Assert;
import org.testng.Reporter;

public class CheckDatabase {

    private static UserAliPayInfo dataUserAliPayInfo;
    private static UserWeChatInfo dataUserWeChatInfos;
    private static UserAddressInfo dataUserAddressInfo;
    private static UserBaseInfo dataBaseUserBaseInfo;
    private static UserLoginInfo dataUserLoginInfo;
    private static UserTaobaoInfo dataUserTaobaoInfo;
    private static UserTeamInfo dataBaseUserTeamInfo;
    private static UserRleationInfo dataBaseUserRleationInfo;

    //数据库检查
    public static void CheckDatabaseUserUserWeChatInfo(UserBaseInfoMapper userBaseInfoMapper,String method, UserAddressInfo userAddressInfo) {
        switch (method) {
            //微信解绑
            case "WeChatInfoUnbind"://微信解除绑定
                dataUserWeChatInfos = userBaseInfoMapper.queryWeChatInfo(userAddressInfo.getChannelUserId());
                Assert.assertEquals(userAddressInfo.getIsDelete(), dataUserWeChatInfos.getIsDelete());
                DataUtils.logDatabase(1,"name",dataUserWeChatInfos.getIsDelete().toString());
                break;
            case "WeChatInfoBind": //微信绑定
                dataUserWeChatInfos = userBaseInfoMapper.queryWeChatInfo(userAddressInfo.getChannelUserId());
                Assert.assertEquals(userAddressInfo.getChannelUserId(), dataUserWeChatInfos.getChannelUserId());
                DataUtils.logDatabase(2, null, dataUserWeChatInfos.toString());
                break;
        }
    }

    public static void CheckDatabaseUserUserAddressInfo(UserBaseInfoMapper userBaseInfoMapper,String method,UserAddressInfo userAddressInfo){
        switch (method) {
            case "AddressUpadate"://更新收货地址
                dataUserAddressInfo = userBaseInfoMapper.queryUserAddressInfo(userAddressInfo.getChannelUserId());
                String name = userAddressInfo.getName();
                Assert.assertEquals(userAddressInfo.getName(), dataUserAddressInfo.getName()); //名称是否有更新
                DataUtils.logDatabase(1,"name",dataUserAddressInfo.getName());
                break;
            case "AddressDelete"://删除收货地址
                dataUserAddressInfo = userBaseInfoMapper.queryUserAddressInfo(userAddressInfo.getChannelUserId());
                int addressIsDelete = userAddressInfo.getIsDelete(); //比对
                Assert.assertEquals(userAddressInfo.getIsDelete(), dataUserAddressInfo.getIsDelete());
                DataUtils.logDatabase(1,"is_delete", dataUserAddressInfo.getIsDelete().toString());
                break;
        }
    }

    //支付宝信息
    public static void CheckDatabaseUserAliPayInfo(UserBaseInfoMapper userBaseInfoMapper,String method,UserAliPayInfo userAliPayInfo){
        switch (method) {
            case "AliPayBind"://支付宝绑定
                dataUserAliPayInfo = userBaseInfoMapper.queryAliPayInfo(userAliPayInfo.getChannelUserId());
                Assert.assertEquals(userAliPayInfo.getChannelUserId(),dataUserAliPayInfo.getChannelUserId());
                DataUtils.logDatabase(2, null, dataUserTaobaoInfo.toString());
                break;
            case "AliPayAuth"://支付宝授权
                dataUserAliPayInfo = userBaseInfoMapper.queryAliPayInfo(userAliPayInfo.getChannelUserId());
                Assert.assertEquals(userAliPayInfo.getStatus(),dataUserAliPayInfo.getStatus());
                DataUtils.logDatabase(1,"Status",dataUserAliPayInfo.getStatus().toString());
                break;
            case "AliPayCancel"://取消授权
                dataUserAliPayInfo = userBaseInfoMapper.queryAliPayInfo(userAliPayInfo.getChannelUserId());
                int unbindStatus = userAliPayInfo.getStatus();
                Assert.assertEquals(userAliPayInfo.getStatus(),dataUserAliPayInfo.getStatus());
                DataUtils.logDatabase(1,"Status",dataUserAliPayInfo.getStatus().toString());
                break;
        }
    }

    //淘宝信息
    public static void CheckDatabaseUserTaobaoInfo(UserBaseInfoMapper userBaseInfoMapper,String method,UserTaobaoInfo userTaobaoInfo){
        switch (method) {
            case "TaoBaoAuth"://淘宝授权
                dataUserTaobaoInfo = userBaseInfoMapper.queryUserTaobaoInfo(userTaobaoInfo.getChannelUserId());
                Assert.assertEquals(userTaobaoInfo.getChannelUserId(),dataUserTaobaoInfo.getChannelUserId());
                DataUtils.logDatabase(2, null, dataUserTaobaoInfo.toString());
                break;
            case "TaoBaoCancel"://取消授权
                dataUserTaobaoInfo = userBaseInfoMapper.queryUserTaobaoInfo(userTaobaoInfo.getChannelUserId());
                Assert.assertEquals(userTaobaoInfo.getTbAccount(),null);
                DataUtils.logDatabase(2, null, dataUserTaobaoInfo.toString());
                break;
        }
    }

    //用户信息
    public static void CheckDatabaseUserBaseInfo(UserBaseInfoMapper userBaseInfoMapper,String method,
                                                 UserBaseInfo userBaseInfo,UserLoginInfo userLoginInfo){
        switch (method) {
            case "NickNameUpdate": //昵称更新
                System.out.println(userBaseInfoMapper);
                dataBaseUserBaseInfo = userBaseInfoMapper.queryUserBaseInfo(userLoginInfo.getChannelUserId());
                Assert.assertEquals(userBaseInfo.getNickName(), dataBaseUserBaseInfo.getNickName());
                DataUtils.logDatabase(1,"nick_name",dataBaseUserBaseInfo.getNickName());
                break;
            case "HeadUrlImg": //修改头像
                dataBaseUserBaseInfo = userBaseInfoMapper.queryUserBaseInfo(userLoginInfo.getChannelUserId());
                Assert.assertEquals(userBaseInfo.getHeadImg(), dataBaseUserBaseInfo.getHeadImg());
                DataUtils.logDatabase(1,"head_img",dataBaseUserBaseInfo.getHeadImg());
                break;
            case "MobileUpadate"://修改手机
                dataUserLoginInfo = userBaseInfoMapper.queryUserLoginInfo(userLoginInfo.getChannelUserId());
                Assert.assertEquals(userBaseInfo.getMobile(), dataUserLoginInfo.getLoginName());
                Reporter.log(PartMsg + "login_name值变更为：" +  dataUserLoginInfo.getLoginName());
                break;
            case "PwdUpdate"://修改密码
                dataUserLoginInfo = userBaseInfoMapper.queryUserLoginInfo(userLoginInfo.getChannelUserId());
                Assert.assertEquals(userLoginInfo.getLoginPwd(), dataUserLoginInfo.getLoginPwd());
                Reporter.log(PartMsg + "login_pwd值变更为：" + dataUserLoginInfo.getLoginPwd());
                break;
        }
    }

    //团长信息
    public static void CheckDatabaseUserTeamInfo(TeamRealtionInfoMapper teamRealtionInfoMapper, String method, UserTeamInfo userTeamInfo) {
        switch (method) {
            case "teamRegister":
                dataBaseUserTeamInfo = teamRealtionInfoMapper.queryUserTeamInfo(userTeamInfo.getChannelUserId());
                Assert.assertEquals(userTeamInfo.getChannelUserId(),dataBaseUserTeamInfo.getChannelUserId());
                break;
            case "teamUpdate":
                dataBaseUserTeamInfo = teamRealtionInfoMapper.queryUserTeamInfo(userTeamInfo.getChannelUserId());
                //读库  预期 ：目标
                Assert.assertEquals(userTeamInfo.getRealName(),dataBaseUserTeamInfo.getRealName());
                //记录
                DataUtils.logDatabase(1, "real_name", dataBaseUserTeamInfo.getRealName());
                break;
            case "teamDelete":
                dataBaseUserTeamInfo = teamRealtionInfoMapper.queryUserTeamInfo(userTeamInfo.getChannelUserId());
                Assert.assertEquals(userTeamInfo.getIsDelete(), dataBaseUserTeamInfo.getIsDelete());
                DataUtils.logDatabase(1, "is_delete", dataBaseUserTeamInfo.getIsDelete() + "");
                break;
        }
    }

    //团长关系
    public static void CheckDatabaseUserUserRleationInfo(TeamRealtionInfoMapper teamRealtionInfoMapper,String method,UserRleationInfo userRleationInfo){
        switch (method) {
            case "relationRegister": //绑定团长关系
                dataBaseUserRleationInfo = teamRealtionInfoMapper.queryUserRleationInfo(userRleationInfo.getChannelUserId());
                Assert.assertEquals(userRleationInfo.getChannelUserId(), dataBaseUserRleationInfo.getChannelUserId());
                DataUtils.logDatabase(2, null, dataBaseUserRleationInfo.toString());
                break;
            case "relationDelete": //解除绑定团长关系
                dataBaseUserRleationInfo = teamRealtionInfoMapper.queryUserRleationInfo(userRleationInfo.getChannelUserId());
                Assert.assertEquals(userRleationInfo.getIsDelete(), dataBaseUserRleationInfo.getIsDelete());
                DataUtils.logDatabase(1, "is_delete", dataBaseUserRleationInfo.getIsDelete() + "");
                break;
        }
    }
}