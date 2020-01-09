package com.example.UserbasePublic;
import com.example.domain.UserRleationInfo;
import com.example.mapper.TeamRealtionInfoMapper;
import com.example.utils.CheckDatabase;
import com.example.utils.DataTransferUtil;
import com.example.utils.DataUtils;
import com.example.utils.HttpConfigUtil;
import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.UserTeamRelationServiceProto;
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
public class UserTeamRelationTest extends AbstractTestNGSpringContextTests {

    //用于单接口场景
    private static Integer channelId=1;
    private static String channelUserId;
    private static Integer appType=1;

    private static CloseableHttpClient httpClient;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;
    private static JsonFormat jsonFormat;
    private static ByteArrayEntity byteArrayEntity;

    @Autowired
    private TeamRealtionInfoMapper teamRealtionInfoMapper;

    @BeforeTest
    public void beforeTest(){
        httpClient = HttpClients.createDefault();
        jsonFormat =new JsonFormat();
        channelUserId=String.valueOf((int)((Math.random()*9+1)*10000));
    }

    @Test(description = "1.绑定(新增)团长关系(幂等)")
    public void teamRelationRegisterTest(){
        try{
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/relation/register", "");
            System.out.println(uri);
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.UserTeamRelationRegisterRequest(channelUserId,channelId,appType,"5201314");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            UserTeamRelationServiceProto.UserTeamRelationRegisterResponse resp = UserTeamRelationServiceProto.UserTeamRelationRegisterResponse.parseFrom(response.getEntity().getContent());
            Reporter.log(resp.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(description = "2.解绑(删除)团长关系(幂等)")
    public void teamRelationDeleteTest(){
        try{
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/relation/delete", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.UserTeamRelationUntyingRequest(channelUserId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
            UserTeamRelationServiceProto.ResponseCode resp = UserTeamRelationServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
            Reporter.log(resp.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test(description = "3.团长关系聚合")
    public void teamRelationCURD(){
        UserRleationInfo userRleationInfo =new UserRleationInfo();
        userRleationInfo.setAppType(1);
        userRleationInfo.setChannelUserId(String.valueOf((int)((Math.random()*9+1)*100000)));//随机6位channeluserid
        userRleationInfo.setTeamUserId("5201314");
        userRleationInfo.setIsDelete(1);

        try{
            //绑定团长关系
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/relation/register", "");
            System.out.println(uri);
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.UserTeamRelationRegisterRequest(userRleationInfo.getChannelUserId(),channelId,userRleationInfo.getAppType(),userRleationInfo.getTeamUserId());
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            jsonFormat =new JsonFormat();
            UserTeamRelationServiceProto.UserTeamRelationRegisterResponse resp = UserTeamRelationServiceProto.UserTeamRelationRegisterResponse.parseFrom(response.getEntity().getContent());
            System.out.println("result:" + jsonFormat.printToString(resp));
            //判断
            DataUtils.logResponse(jsonFormat.printToString(resp));
            CheckDatabase.CheckDatabaseUserUserRleationInfo(teamRealtionInfoMapper,"relationRegister",userRleationInfo);

            //解除团长
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/relation/delete", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.UserTeamRelationUntyingRequest(userRleationInfo.getChannelUserId());
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
            UserTeamRelationServiceProto.ResponseCode resp1 = UserTeamRelationServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
            //判断
            DataUtils.logResponse(resp1.toString());
            CheckDatabase.CheckDatabaseUserUserRleationInfo(teamRealtionInfoMapper,"relationDelete",userRleationInfo);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterTest
    public void afterTest() throws IOException {
        httpClient.close();
    }
}
