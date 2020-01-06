package com.example.UserbasePublic;

import com.example.utils.HttpConfigUtil;
import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.UserTeamInfoServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.net.URI;


@SpringBootTest
public class UserTeamInfoTest extends AbstractTestNGSpringContextTests{

    private static Integer channelId=1;
    private static CloseableHttpClient httpClient;
    private static ByteArrayEntity byteArrayEntity;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;

    @Test
    public void userTeamInfoRegisterChannelUserIdTest() {
        try {
            //Assert.assertEquals("RESP_CODE_SUCCESS",authResponseMsg);
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
            JsonFormat jsonFormat = new JsonFormat();
            Assert.assertEquals(200,response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.UserTeamInfoRegisterResponse resp = UserTeamInfoServiceProto.UserTeamInfoRegisterResponse.parseFrom(response.getEntity().getContent());
            System.out.println(jsonFormat.printToString(resp));
            Reporter.log(jsonFormat.printToString(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
