package com.example.UserbasePublic.BuFengZhuang;
import com.example.utils.HttpConfigUtil;
import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.UserTeamInfoServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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
public class UserTeamInfoTest extends AbstractTestNGSpringContextTests{

    private static Integer channelId=1;
    private static CloseableHttpClient httpClient;
    private static ByteArrayEntity byteArrayEntity;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;
    private static JsonFormat JsonFormat;
    private static String incomeMessage="传入参数:"+" {";

    @BeforeTest
    public void beforeTest(){
        httpClient = HttpClients.createDefault();
        JsonFormat =new JsonFormat();
    }

    //@Test(description ="注册团长信息(幂等)")
    public void userTeamInfoRegisterChannelUserIdTest() {
        try {
            //Assert.assertEquals("RESP_CODE_SUCCESS",authResponseMsg);
            //注册团长
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/register", "");
            post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
            builder.setAppType(1);
            builder.setRealName("周雄鑫");
            builder.setChannelId(1);
            builder.setChannelUserId("5201314");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);

            Assert.assertEquals(200,response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.UserTeamInfoRegisterResponse resp = UserTeamInfoServiceProto.UserTeamInfoRegisterResponse.parseFrom(response.getEntity().getContent());
            System.out.println(JsonFormat.printToString(resp));
            Reporter.log(JsonFormat.printToString(resp));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test(description ="修改团长信息(幂等)")
    public void userTeamInfoUpdateChannelUserIdTest(){
        try {

            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/update", "");
            post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.newBuilder();
            UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder entity =UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
            entity.setAppType(1);
            entity.setChannelId(1);
            entity.setChannelUserId("5201314");
            entity.setRealName("xiongxinzhou");
            builder.setUpdateRequest(entity.build());

            Reporter.log(incomeMessage+"}");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);

            Assert.assertEquals(200,response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.ResponseCode resp = UserTeamInfoServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
            System.out.println(JsonFormat.printToString(resp));
            Reporter.log(JsonFormat.printToString(resp));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description ="根据批量channelUserId查询团长信息(幂等)")
    public void fansTeamInfoQueryBatchAppTypeTest(){
        try {
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/query/batch", "");
            post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoQueryBatchRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoQueryBatchRequest.newBuilder();
            builder.setAppType(1);
            builder.setChannelId(1);
            builder.addChannelUserId("5201314");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);

            Assert.assertEquals(200,response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.UserTeamInfoQueryBatchResponse resp = UserTeamInfoServiceProto.UserTeamInfoQueryBatchResponse.parseFrom(response.getEntity().getContent());
            System.out.println("result:" + JsonFormat.printToString(resp));
            Reporter.log(JsonFormat.printToString(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }




    }









    @AfterTest
    public void afterTest() throws IOException {httpClient.close(); }
}
