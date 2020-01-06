package com.example.UserbasePublic;

import com.example.mapper.UserBaseInfoMapper;
import com.example.utils.*;
import com.hs.user.base.proto.UserBaseServiceProto;
import com.hs.user.base.proto.UserWeChatAuthServiceProto;
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

@SpringBootTest
public class UserWeChatTest extends AbstractTestNGSpringContextTests{

    //10个接口

    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;//数据库取数据用

    private static String AppId="Appid01";
    private static Integer channelId =1;
    private static CloseableHttpClient httpClient;
    private static ByteArrayEntity byteArrayEntity;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;

    /**
     * 1.微信绑定:随机生成 AppId, channelId,channelUserId,openId
     * 2.微信解绑：openId, channelId,channelUserId,AppId
     * 3.用户一键登录微信：channelId,mobile,inviteChannelUserId,mobileAreaCode
     * 4.用户微信登录(幂等)：channelId,channelUserId,openId,appId
     * 5.根据openId查询用户微信列表信息：channelId,openId,appId
     * 6.根据渠道用户Id查询用户微信列表信息(幂等)：channelId,channelUserId,appId
     * 7.设置用户微信号(幂等) 只返回成功与失败
     */

    @BeforeTest
    public void beforeTest(){
        httpClient = HttpClients.createDefault();
    }


    @Test(description = "1.微信绑定" +
            "            2.微信解绑 ")
    public void bindingAndunBindingTest(){
        String openId= DataUtils.getRandomString(9);    //随机生成openId
        String channelUserId=String.valueOf((int)((Math.random()*9+1)*1000)); //随机生成ChannelUserId
        try {
            //微信绑定
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/binding","");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userWeChatAuthRequest(AppId, channelId,channelUserId,openId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String bindResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",bindResponseMsg);
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"WeChatInfoBind",channelUserId,channelUserId);

            //解除绑定
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/unBinding","");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userWeChatAuthUnBindRequest(openId, channelId,channelUserId,AppId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String unbindResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",unbindResponseMsg);
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"WeChatInfoUnbind","null",channelUserId);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test(description = "3.用户一键登录微信")
    public void loginByOneKeyTest(){ ;
        try{
            String mobile="17702015334";
            String inviteChannelUserId="177417";
            String mobileAreaCode="86";

            //用户一键登录微信
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/loginByOneKey","");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userWeChatOneKeyLoginRequest(channelId,mobile,inviteChannelUserId,mobileAreaCode);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response,UserWeChatAuthServiceProto.UserWeChatAuthInfoResponse.class);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(description = "4.用户微信登录(幂等)" +
                        "5.根据openId查询用户微信列表信息 ")
    public void weChatLoginAndGetInfoByOpenIdTest(){
        try{
            String openId="oBrt31Sg6EqD9DJxB0Mz9EOl-Pp4";
            String appId="Appid01";
            String channelUserId="3692091";

            //用户微信登录(幂等)
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/login","");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.UserWeChatAuthLoginRequest(channelId,channelUserId,openId,appId,"login");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response,UserWeChatAuthServiceProto.UserWeChatAuthLoginResponse.class);

            //根据openId查询用户微信列表信息
            httpClient=HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/getWeChatByOpenId","");
            post = new HttpPost(uri);;
            byteArrayEntity =  DataTransferUtil.getUserWeChatAuthByOpenIdRequest(channelId,openId,appId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserWeChatAuthServiceProto.UserWeChatAuthInfoResponse.class);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(description = "6.根据渠道用户Id查询用户微信列表信息(幂等)")
    public void getWeChatByChannelUserIdTest(){
        try{
            String channelUserId="3692091";
            String appId="Appid01";

            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/getWeChatByChannelUserId","");
            post = new HttpPost(uri);;
            byteArrayEntity =  DataTransferUtil.getUserWeChatAuthByChannelUserIdRequest(channelId,channelUserId,appId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserWeChatAuthServiceProto.UserWeChatAuthInfoResponseList.class);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(description = "7.检查手机号绑定")//这里检查的是userbase表里面的mobile
    public void checkPhoneTest(){
       try{
           String channelUserId="3692091";
           String openId="oBrt31Sg6EqD9DJxB0Mz9EOl-Pp4";

           uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/checkPhone","");
           post = new HttpPost(uri);;
           byteArrayEntity =  DataTransferUtil.UserWeChatAuthCheckPhoneRequest(channelId,channelUserId,openId);
           post.setEntity(byteArrayEntity);
           post.setHeader("Content-Type", "application/x-protobuf");
           response = httpClient.execute(post);
           CheckReponseResult.AssertResponses(response, UserWeChatAuthServiceProto.UserWeChatAuthCheckPhoneResponse.class);
       }catch (Exception e){
            e.printStackTrace();
       }
    }

    //@Test(description = "8.设置用户微信号(幂等) 只返回成功与失败 x")
    public void setWxNoChannelIdTest(){
        try{

            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/wxno/set","");
            post = new HttpPost(uri);;
            byteArrayEntity = DataTransferUtil.UserWeChatWxNoRequest();
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponse(response);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(description = "9.设置用户微信号(幂等) 只返回成功与失败 x")
    public void getWeChatUserByOpenIdOrUnionIdTest(){

        String openId="oBrt31Sg6EqD9DJxB0Mz9EOl-Pp4";
        String appId="Appid01";
        String channelUserId="3692091";

        try{

            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/getWeChatUserByOpenIdOrUnionId","");
            post = new HttpPost(uri);;
            byteArrayEntity =  DataTransferUtil.UserWeChatAuthLoginRequest(channelId,channelUserId,openId,appId,"getWeChatUserByOpenIdOrUnionId");
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserWeChatAuthServiceProto.UserWeChatAuthInfoResponse.class);

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @AfterTest
    public void afterTest() throws IOException {
        httpClient.close();
    }

}
