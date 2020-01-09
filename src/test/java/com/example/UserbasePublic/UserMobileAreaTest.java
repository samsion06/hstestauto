package com.example.UserbasePublic;
import com.example.utils.DataTransferUtil;
import com.example.utils.HttpConfigUtil;
import com.googlecode.protobuf.format.JsonFormat;
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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;

@SpringBootTest
public class UserMobileAreaTest extends AbstractTestNGSpringContextTests {

    private static Integer channelId =1;
    private static CloseableHttpClient httpClient;
    private static ByteArrayEntity byteArrayEntity;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;

    @BeforeTest
    public void beforeTest(){
        httpClient = HttpClients.createDefault();
    }

    @Test(description = "用户手机国际区号查询(幂等)")
    public void serMobileAreaCodeListTest(){
        try{
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/user/mobile/area/list/query", null);
            post = new HttpPost(uri);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
            UserMobileAreaServiceProto.UserMobileAreaCodeListResponse resp = UserMobileAreaServiceProto.UserMobileAreaCodeListResponse.parseFrom(response.getEntity().getContent());
            Assert.assertFalse(resp.getUserMobileAreaCodeList().isEmpty(),"返回的对象为空");
            Reporter.log(resp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Test(description = "根据手机国际区号查询手机国际区号信息查询(幂等)")
    public void userMobileAreaCodeQueryTest() {
        try {
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/user/mobile/area/query", null);
            post = new HttpPost(uri);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            byteArrayEntity = DataTransferUtil.UserMobileAreaCodeRequest("86","CN");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserMobileAreaServiceProto.UserMobileAreaCodeResponse resp = UserMobileAreaServiceProto.UserMobileAreaCodeResponse.parseFrom(response.getEntity().getContent());
                if (UserMobileAreaServiceProto.UserMobileAreaCode.getDefaultInstance() ==resp.getUserMobileAreaCode()|| UserMobileAreaServiceProto.UserMobileAreaCode.getDefaultInstance().equals(resp.getUserMobileAreaCode())) {
                    System.out.println("Is Empty");
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(description = "手机国际区号缓存刷新(幂等)")
    public void userMobileAreaCodeCacheRefreshTest(){
        try {
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/user/mobile/area/cache/refresh", null);
            HttpPost post = new HttpPost(uri);
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            Reporter.log("手机号缓存刷新_");
            JsonFormat jsonFormat = new JsonFormat();
            Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
            UserMobileAreaServiceProto.ResponseCode resp = UserMobileAreaServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
            Reporter.log(resp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void afterTest() throws IOException {
        httpClient.close();
    }
}
