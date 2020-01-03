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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

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


    @BeforeTest
    public void beforeTest(){
        httpClient = HttpClients.createDefault();
    }

    @org.testng.annotations.Test(description = "1.实名认证" +
                                               "2.实名认证查询")
    public void identifyAndQueryStatusTest() {

        String attachmentUrl="http://images.huasheng100.com/public/1573632666839292.jpg;http://images.huasheng100.com/public/1573632674240333.jpg";
        String channeluserId = "56903884418";
        String realName="尹小芳";
        String idCardNum="430525199204064942";

            //实名认证 主要看userbase的字段
        try {

            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/user/idCard/identify", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.UserIdCardIdentifyRequestConvertBuilder(channeluserId, channelId,realName,idCardNum,attachmentUrl);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponse(response);

            //实名认证查询 主要看user_attachment 的channeluserid
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/user/idCard/queryStatus", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userIdCardStatusQueryRequestConvertBuilder(channeluserId, channelId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserIdCardIdentifyServiceProto.UserIdCardIdentifyInfo.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void afterTest() throws IOException {
            httpClient.close();
    }
}