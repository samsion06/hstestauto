package com.example.UserbasePublic;

import com.example.utils.CheckReponseResult;
import com.example.utils.HttpConfigUtil;
import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.UserAddressServiceProto;
import com.hs.user.base.proto.UserMobileAreaServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.net.URI;

@SpringBootTest
public class UserMobileAreaTest extends AbstractTestNGSpringContextTests {

    private static Integer channelId =1;
    private static CloseableHttpClient httpClient;
    private static ByteArrayEntity byteArrayEntity;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;

    @Test(description = "用户手机国际区号查询(幂等)")
    public void serMobileAreaCodeList_Empty_Test(){
        try{
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/user/mobile/area/list/query", null);
            post = new HttpPost(uri);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
            UserMobileAreaServiceProto.UserMobileAreaCodeListResponse resp = UserMobileAreaServiceProto.UserMobileAreaCodeListResponse.parseFrom(response.getEntity().getContent());
            Assert.assertTrue(resp.getUserMobileAreaCodeList().isEmpty(),"返回的对象为空");
            System.out.println(resp);
            Reporter.log(resp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
