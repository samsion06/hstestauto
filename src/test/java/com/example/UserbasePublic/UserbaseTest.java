package com.example.UserbasePublic;
import com.example.domain.HsrjUserInfo;
import com.example.domain.UserBaseInfo;
import com.example.domain.UserLoginInfo;
import com.example.mapper.UserBaseInfoMapper;
import com.example.utils.*;
import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.UserBaseServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.URI;

//https://blog.csdn.net/qq_16605855/article/details/81183990(testNg集成SpringBoot)
@SpringBootTest
public class UserbaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;//数据库取数据用

    private static CloseableHttpClient httpClient;
    private static ByteArrayEntity byteArrayEntity;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;


    private static String nickname;
    private static String headimgurl;
    private static String pushNo;
    private static Integer channelId=1;
    //178803 这个channeluserid不能随便改

    @BeforeTest
    public void beforeTest(){
        httpClient = HttpClients.createDefault();
    }

    @Test(description = "1.用户登录" +
                     "   2.获取用户基础信息" +
                     "   3.修改昵称" +
                     "   4.修改头像" +
                     "   5.修改用户邀请码" +
                     "   6.修改用户标签")
    public void LoginAndUpdate() {//注册后user_base_info,user_login_info,hsrj_user_info 三个表都会有数据,user_base_info登录得时候的mobile_area_code有值就要传递
        //生成动态数据
        String nickname=DataUtils.getRandomString(9);//随机生成用户名;
        String headimgurl=DataUtils.getRandomString(15);//随机生成头像;
        String pushNo=DataUtils.getRandomString(6);//随机生成邀请码

        //用户信息
        UserBaseInfo userBaseInfo=new UserBaseInfo();
        userBaseInfo.setNickName(nickname);
        userBaseInfo.setHeadImg(headimgurl);

        //用户登陆
        UserLoginInfo userLoginInfo= new UserLoginInfo();
        userLoginInfo.setLoginName("12352040885");//176735
        userLoginInfo.setLoginPwd("123456");
        userLoginInfo.setChannelId(1);

        HsrjUserInfo hsrjUserInfo=new HsrjUserInfo();
        hsrjUserInfo.setPushNo(pushNo.toLowerCase());
        hsrjUserInfo.setChannelUserId("176735");

        System.out.println("穿进去的pushno"+pushNo);
        try {
            //登录
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/info/pd/login", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userInfoPdLoginRequestConvertBuilder(channelId, userLoginInfo.getLoginName(), userLoginInfo.getLoginPwd(), "86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String result = CheckReponseResult.AssertResponses(response, UserBaseServiceProto.userInfoPdCombine.class);
            //获取channeluserid传入下个接口
            userLoginInfo.setChannelUserId(DataUtils.substring(result, "userId", 10, ",", 1));
            System.out.println(userLoginInfo.getChannelUserId());

            //获取用户基础信息
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/getUserBaseInfo","");
            post = new HttpPost(uri);;
            byteArrayEntity = DataTransferUtil.UserInfoRequest(channelId,userLoginInfo.getChannelUserId());
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserBaseServiceProto.UserBaseInfo.class);

            //修改昵称
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/nick/name/update", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userNickNameUpdateRequestConvertBuilder(channelId, userLoginInfo.getChannelUserId(), userBaseInfo.getNickName());
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String nickNameResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS", nickNameResponseMsg);
            CheckDatabase.CheckDatabaseUserBaseInfo(userBaseInfoMapper,"NickNameUpdate", userBaseInfo, userLoginInfo);

            //修改头像
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/head/img/update", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userHeadImgUpdateRequestConvertBuilder(channelId, userLoginInfo.getChannelUserId(), userBaseInfo.getHeadImg());
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String headUrlImg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS", headUrlImg);
            CheckDatabase.CheckDatabaseUserBaseInfo(userBaseInfoMapper,"HeadUrlImg", userBaseInfo, userLoginInfo);

            //修改用户邀请码
            uri = new URI(HttpConfigUtil.scheme, null, "user-base.huasheng100.com", 80, "/base/user/invite/code/update", "", null);
            post = new HttpPost(uri);;
            byteArrayEntity = DataTransferUtil.UserInviteCodeUpdateRequest(channelId, hsrjUserInfo.getChannelUserId(), hsrjUserInfo.getPushNo());
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
            UserBaseServiceProto.ResponseCode respc=UserBaseServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
            JsonFormat jsonFormat =new JsonFormat();
            Assert.assertEquals("{\"msg\": \"RESP_CODE_SUCCESS\"}", jsonFormat.printToString(respc));
            DataUtils.logResponse(jsonFormat.printToString(respc));
            //检查数据库
            CheckDatabase.CheckDatabaseHsrjUserInfo(userBaseInfoMapper,"pushNoUpdate",hsrjUserInfo);

            hsrjUserInfo.setUserTagStatus(3);
            System.out.println(hsrjUserInfo);
            //修改用户标签
            uri = new URI(HttpConfigUtil.scheme, null, "user-base.huasheng100.com", 80, "/base/user/tag/update", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.UserInfoTagRequest("178803",hsrjUserInfo.getUserTagStatus());
            System.out.println(hsrjUserInfo.getUserTagStatus());
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String userStatusTag = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS", userStatusTag);
            System.out.println("====================="+hsrjUserInfo);
            //检查数据库
            CheckDatabase.CheckDatabaseHsrjUserInfo(userBaseInfoMapper,"userTagStatusUpdate",hsrjUserInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "1.根据邀请码获取用户信息" +
                        "2.根据邀请码获取用户信息(幂等)")
    public void getInfoByInviteCode() {
        //用户信息
        UserBaseInfo userBaseInfo=new UserBaseInfo();
        userBaseInfo.setChannelId(1);

        try {
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/get/by/invite/code", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userInviteCodeQueryRequest("p88vcdo", userBaseInfo.getChannelId(),"UserInfoInviteCodeResponse");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserBaseServiceProto.UserInfoInviteCodeResponse.class);

            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/info/pd/get/by/invite/code", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userInviteCodeQueryRequest("p88vcdo", userBaseInfo.getChannelId(),"userInfoPdCombine");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserBaseServiceProto.userInfoPdCombine.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "1.根据手机号码获取用户信息" +
                        "2.根据手机号码获取用户信息（聚合）")
    public void getUserInfoByMobile(){//规则：userbase里面得手机号和要在映射表userindx里面得indexid有才行
        try{
            //根据手机号码获取用户信息
            UserBaseInfo userBaseInfo=new UserBaseInfo();
            userBaseInfo.setMobile("18756989065");
            userBaseInfo.setChannelId(1);

            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/getUserInfoByMobile", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.UserInfoByMobileRequest(userBaseInfo.getMobile(),"86",userBaseInfo.getChannelId(),"UserBaseInfo");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response,UserBaseServiceProto.UserBaseInfo.class);

            //根据手机号码获取用户信息（聚合）
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/info/pd/get/by/mobile", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.UserInfoByMobileRequest(userBaseInfo.getMobile(),"86",userBaseInfo.getChannelId(),"userInfoPdCombine");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response,UserBaseServiceProto.userInfoPdCombine.class);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(description = "1.修改手机号" +
                        "2.修改密码" +
                        "3.登录")
    public void mdfMobileAndPwdUpdateTest(){

        UserLoginInfo userLoginInfo=new UserLoginInfo();
        userLoginInfo.setChannelUserId("178803");//178803
        userLoginInfo.setLoginName("123"+(int)((Math.random()*9+1)*10000000));//修改登陆手机号
        userLoginInfo.setChannelId(1);

        try{

            //修改手机号
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/mobile/update", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userMobileUpdateRequestConvertBuilder(userLoginInfo.getChannelId(), userLoginInfo.getLoginName(), userLoginInfo.getChannelUserId(), "86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String mobileResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",mobileResponseMsg);
            CheckDatabase.CheckDatabaseUserBaseInfo(userBaseInfoMapper,"MobileUpdate",null,userLoginInfo);

            //178803 将密码转换成MD5加密方式
            String pwd="123456";
            String md5pwd = MD5Util.toMD5(pwd.trim().toUpperCase());
            userLoginInfo.setLoginPwd(md5pwd);
            //修改登录密码
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/user/pwd/update", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userPwdUpdateRequestConvertBuilder(userLoginInfo.getChannelUserId(), userLoginInfo.getChannelId(), userLoginInfo.getLoginPwd());
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String pwdResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",pwdResponseMsg);
            CheckDatabase.CheckDatabaseUserBaseInfo(userBaseInfoMapper,"PwdUpdate",null,userLoginInfo);
            //再次登录
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/info/pd/login", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userInfoPdLoginRequestConvertBuilder(userLoginInfo.getChannelId(), userLoginInfo.getLoginName(), pwd, "86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserBaseServiceProto.userInfoPdCombine.class);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test(description = "忘记密码(修改密码)")
    public void forgetPasswordTest(){
        try{
            UserLoginInfo userLoginInfo=new UserLoginInfo();
            String loginPwd="123456";
            String md5pwd = MD5Util.toMD5(loginPwd.trim().toUpperCase());
            userLoginInfo.setChannelId(1);
            userLoginInfo.setLoginPwd(md5pwd);
            userLoginInfo.setLoginName("15053755782");
            //用户忘记登录密码
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/user/forget/pwd", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userForgetPwdRequest(userLoginInfo.getChannelId(),userLoginInfo.getLoginPwd(),userLoginInfo.getLoginName(),"86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            //校验结果
            CheckReponseResult.AssertResponse(response);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(description = "根据查询条件查询用户列表")
    public void getUsersByConditionTest(){
        try{
            UserBaseInfo userBaseInfo=new UserBaseInfo();
            userBaseInfo.setChannelUserId("178803");
            userBaseInfo.setChannelId(1);
            //根据查询条件查询用户列表
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/getUsersByCondition", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.UserBaseInfoByConditionRequest(userBaseInfo.getChannelUserId(),userBaseInfo.getChannelId());
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            //校验结果
            CheckReponseResult.AssertResponses(response,UserBaseServiceProto.UserBaseInfoByConditionPage.class);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(description = "根据微信ID和OPENID获取用户信息")
    public void getByOpenIdAndUnionldIdTest() { //channeluserid 178803
        try {
            UserBaseInfo userBaseInfo=new UserBaseInfo();
            userBaseInfo.setChannelId(1);
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/info/pd/get/by/unionId/openId","");
            System.out.println(uri);
            post = new HttpPost(uri);;
            byteArrayEntity = DataTransferUtil.userInfoUnionIdOpenIdRequestConvertBuilder(userBaseInfo.getChannelId(), "ox-FY1dRO31w9tcGLFh9YiBQQOy8", "oBrt31RJksETS7FWsakEes61W38k");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserBaseServiceProto.userInfoPdCombine.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @AfterTest
    public void afterTest() throws IOException {
        httpClient.close();
    }
}



