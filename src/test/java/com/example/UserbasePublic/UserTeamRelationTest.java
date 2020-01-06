package com.example.UserbasePublic;

import com.example.utils.CheckReponseResult;
import com.example.utils.DataTransferUtil;
import com.example.utils.HttpConfigUtil;
import com.hs.user.base.proto.UserTeamRelationServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;

@SpringBootTest
public class UserTeamRelationTest extends AbstractTestNGSpringContextTests {

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

    @Test(description = "1.绑定(新增)团长关系(幂等)")
    public void teamRelationRegisterTest(){
        try{

            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/user/team/relation/register", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.UserTeamRelationRegisterRequest("1",1,1,"1");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserTeamRelationServiceProto.UserTeamRelationRegisterResponse.class);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(description = "2.解绑(删除)团长关系(幂等)")
    public void teamRelationDeleteTest(){
        try{

            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/user/team/relation/delete", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.UserTeamRelationUntyingRequest();
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
            UserTeamRelationServiceProto.ResponseCode resp = UserTeamRelationServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
            System.out.println(resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterTest
    public void afterTest() throws IOException {
        httpClient.close();
    }
}
