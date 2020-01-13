package com.example.UserbasePublic;

import com.example.domain.UserTaobaoInfo;
import com.example.mapper.UserBaseInfoMapper;
import com.example.utils.*;
import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.HsrjUserTaobaoAuthInfoServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.URI;

@SpringBootTest
public class UserTaobaoTest extends AbstractTestNGSpringContextTests {

    //淘宝中心3个接口

    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;
    private static Integer channelId=1;
    private static CloseableHttpClient httpClient;
    private static ByteArrayEntity byteArrayEntity;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;

    @BeforeTest
    public void beforeTest(){
        httpClient = HttpClients.createDefault();
    }

    @Test(description = "1.淘宝授权 6个参数齐" +
            "            2.授权查询 4个参数齐" +
            "            3.取消授权 1个参数齐")
    public void authAndCancelTest(){
        try {
            UserTaobaoInfo userTaobaoInfo =new UserTaobaoInfo();
            userTaobaoInfo.setChannelUserId("184003");
            userTaobaoInfo.setTbAccount("327420130");
            userTaobaoInfo.setCompanyId(1642L);
            userTaobaoInfo.setTbAccountId(327420130L);

            //淘宝授权 184003
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/taobao/auth", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.HsrjUserTaobaoAuthRequest(userTaobaoInfo.getChannelUserId(),
                    528467632L,528467634L,userTaobaoInfo.getCompanyId(),userTaobaoInfo.getTbAccountId(),userTaobaoInfo.getTbAccount());
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String authResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",authResponseMsg);
            CheckDatabase.CheckDatabaseUserTaobaoInfo(userBaseInfoMapper,"TaoBaoAuth",userTaobaoInfo);

            //授权查询
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/taobao/auth/info", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.HsrjUserTaobaoAuthQueryRequest(userTaobaoInfo.getChannelUserId(),userTaobaoInfo.getTbAccount()
                    ,userTaobaoInfo.getCompanyId(),userTaobaoInfo.getTbAccountId());
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthInfoResponse resp = HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthInfoResponse.parseFrom(response.getEntity().getContent());
            JsonFormat jsonFormat =new JsonFormat();
            DataUtils.logResponse(jsonFormat.printToString(resp));

            Thread.sleep(3000L);

            //取消收授权 TaoBaoCancel
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/taobao/auth/cancel", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.HsrjUserTaobaoAuthCancelRequest(userTaobaoInfo.getChannelUserId());
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String cancelResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",cancelResponseMsg);
            CheckDatabase.CheckDatabaseUserTaobaoInfo(userBaseInfoMapper,"TaoBaoCancel",userTaobaoInfo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   //@Test 取消授权
    public void test(){
        UserTaobaoInfo userTaobaoInfo =new UserTaobaoInfo();
        userTaobaoInfo.setChannelUserId("184003");
        try{
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/taobao/auth/cancel", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.HsrjUserTaobaoAuthCancelRequest(userTaobaoInfo.getChannelUserId());
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String cancelResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",cancelResponseMsg);
            CheckDatabase.CheckDatabaseUserTaobaoInfo(userBaseInfoMapper,"TaoBaoCancel",userTaobaoInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterTest
    public void afterTest() throws IOException {
        httpClient.close();
    }
}
