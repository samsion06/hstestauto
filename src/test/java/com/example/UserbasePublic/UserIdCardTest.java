package com.example.UserbasePublic;

import com.example.utils.CheckReponseResult;
import com.example.utils.DataTransferUtil;
import com.example.utils.HttpConfigUtil;
import com.hs.user.base.proto.UserIdCardIdentifyServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.io.IOException;
import java.net.URI;

@SpringBootTest
public class UserIdCardTest extends AbstractTestNGSpringContextTests {

    private static Integer channelId = 1;
    private static CloseableHttpClient httpClient;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;
    private static ByteArrayEntity byteArrayEntity;

    @org.testng.annotations.Test(description = "1.实名认证" +
            "                                   2.实名认证查询")
    public void test6() {
        String channeluserId = "56903884418";
        try {
//            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/user/idCard/identify","");
//            post = new HttpPost(uri);
//            byteArrayEntity = ConvertData.UserIdCardIdentifyRequestConvertBuilder(channeluserId, 1, "向亚运","431224199009227572","http://www.baidu.com");
//            post.setEntity(byteArrayEntity);
//            post.setHeader("Content-Type", "application/x-protobuf");
//            response = httpClient.execute(post);
//            CheckReponseResult.AssertResponse(response);
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/user/idCard/queryStatus", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userIdCardStatusQueryRequestConvertBuilder(channeluserId, channelId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserIdCardIdentifyServiceProto.UserIdCardIdentifyInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
            }
        }
    }
}