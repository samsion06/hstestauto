package com.example.UserbasePublic.BuFengZhuang;
import com.example.domain.UserTeamInfo;
import com.example.mapper.TeamRealtionInfoMapper;
import com.example.utils.CheckDatabase;
import com.example.utils.DataTransferUtil;
import com.example.utils.DataUtils;
import com.example.utils.HttpConfigUtil;
import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.UserTeamInfoServiceProto;
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
public class UserTeamInfoTest extends AbstractTestNGSpringContextTests{

    @Autowired
    private TeamRealtionInfoMapper teamRealtionInfoMapper;

    private static CloseableHttpClient httpClient;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;
    private static JsonFormat jsonFormat;
    private static ByteArrayEntity byteArrayEntity;
    private String channelUserId;

    @BeforeTest
    public void beforeTest(){
        httpClient = HttpClients.createDefault();
        jsonFormat =new JsonFormat();
    }

    @Test(description ="注册团长信息(幂等)",priority = 1)
    public void userTeamInfoRegisterChannelUserIdTest() {
        try {
            channelUserId =DataUtils.getRandomString(6);
            System.out.println("单接口注册团长生成channeluserid："+channelUserId);
            //注册团长
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/register", "");
            post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
            builder.setAppType(2);
            builder.setChannelId(1);
            builder.setRealName("周雄鑫");
            builder.setChannelUserId(channelUserId);
            DataUtils.logBuilder(builder,"注册团长信息(幂等)_");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);

            Assert.assertEquals(200,response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.UserTeamInfoRegisterResponse resp = UserTeamInfoServiceProto.UserTeamInfoRegisterResponse.parseFrom(response.getEntity().getContent());
            System.out.println(jsonFormat.printToString(resp));
            Reporter.log(jsonFormat.printToString(resp));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description ="删除团长信息(幂等)",priority = 2)
    public void fansTeamInfoDeleteAppTypeTest(){
        try {
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/delete", "");
            post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoDeleteRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoDeleteRequest.newBuilder();
            UserTeamInfoServiceProto.UserTeamInfoCommonRequest.Builder commonRequest = UserTeamInfoServiceProto.UserTeamInfoCommonRequest.newBuilder();
            commonRequest.setAppType(2);
            commonRequest.setChannelId(1);
            commonRequest.setChannelUserId(channelUserId);
            builder.setDeleteRequest(commonRequest);
            DataUtils.logBuilder(builder,"删除团长信息(幂等)_");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            jsonFormat =new JsonFormat();

            Assert.assertEquals(200,response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.ResponseCode resp = UserTeamInfoServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
            System.out.println("result:" + jsonFormat.printToString(resp));
            Reporter.log(jsonFormat.printToString(resp));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description ="修改团长信息(幂等)",priority = 3)
    public void userTeamInfoUpdateChannelUserIdTest(){
        try {
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/update", "");
            post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.newBuilder();
            UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder entity =UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
            entity.setAppType(2);
            entity.setChannelId(1);
            entity.setChannelUserId("Ocr6NZ");
            entity.setRealName("xiongxinzhou");
            entity.setStatus(0);
            builder.setUpdateRequest(entity.build());
            DataUtils.logBuilder(builder,"修改团长信息(幂等)_");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);

            Assert.assertEquals(200,response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.ResponseCode resp = UserTeamInfoServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
            //Assert.assertEquals("RESP_CODE_SUCCESS",resp.getMsg());
            System.out.println(jsonFormat.printToString(resp));
            Reporter.log(jsonFormat.printToString(resp));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description ="根据批量channelUserId查询团长信息(幂等)",priority = 4)
    public void fansTeamInfoQueryBatchAppTypeTest(){
        String channelUserId =DataUtils.getRandomString(6);
        try {
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/query/batch", "");
            post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoQueryBatchRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoQueryBatchRequest.newBuilder();
            builder.setAppType(2);
            builder.setChannelId(1);
            builder.addChannelUserId("Ocr6NZ");
            DataUtils.logBuilder(builder,"根据批量channelUserId查询团长信息(幂等)_");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            Assert.assertEquals(200,response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.UserTeamInfoQueryBatchResponse resp = UserTeamInfoServiceProto.UserTeamInfoQueryBatchResponse.parseFrom(response.getEntity().getContent());
            System.out.println("result:" + jsonFormat.printToString(resp));
            Reporter.log(jsonFormat.printToString(resp));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description ="查询粉丝团长(幂等)",priority = 5)
    public void fansTeamInfoQueryChannelUserIdTest(){
        try {
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/query", "");
            HttpPost post = new HttpPost(uri);
            UserTeamInfoServiceProto.FansTeamInfoQueryRequest.Builder builder = UserTeamInfoServiceProto.FansTeamInfoQueryRequest.newBuilder();
            UserTeamInfoServiceProto.UserTeamInfoCommonRequest.Builder commonRequest = UserTeamInfoServiceProto.UserTeamInfoCommonRequest.newBuilder();
            commonRequest.setAppType(2);
            commonRequest.setChannelId(1);
            commonRequest.setChannelUserId("Ocr6NZ");
            builder.setCommonRequest(commonRequest);
            DataUtils.logBuilder(builder,"查询粉丝团长(幂等)_");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            jsonFormat =new JsonFormat();
            Assert.assertEquals(200,response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.FansTeamInfoQueryResponse resp = UserTeamInfoServiceProto.FansTeamInfoQueryResponse.parseFrom(response.getEntity().getContent());
            System.out.println("result:" + jsonFormat.printToString(resp));
            Reporter.log(jsonFormat.printToString(resp));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description ="1.注册团长" +
                       "2.根据批量channelUserId查询团长信息" +
                       "3.修改团长" +
                       "4.查询粉丝团长" +
                       "5.删除团长信息")
    public void userTeamCURD(){
        try {
            String channelUserId=(int)((Math.random()*9+1)*100000)+"";
            UserTeamInfo userTeamInfo=new UserTeamInfo(1,channelUserId,"xiongxinzhou",1, 5201314l,"周雄鑫",
                    "5201314",5201314l,5201314l,"17702015334",
                    5201314l,1,1,1,"5201314","周雄鑫",
                    5201314l,5201314l,"5201314","17702015334",5201314l,
                    "5201314",1,"www.baidu.com",1, "17702015334","周氏集团",
                    1);

            //1.注册团长 29
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/register", "");
            post = new HttpPost(uri);
            byteArrayEntity= DataTransferUtil.UserTeamInfoRegisterRequest(userTeamInfo);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            Assert.assertEquals(200, response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.UserTeamInfoRegisterResponse resp = UserTeamInfoServiceProto.UserTeamInfoRegisterResponse.parseFrom(response.getEntity().getContent());
            System.out.println(jsonFormat.printToString(resp));
            DataUtils.logResponse(jsonFormat.printToString(resp));
            CheckDatabase.CheckDatabaseUserTeamInfo(teamRealtionInfoMapper, "teamRegister", userTeamInfo);

            //2.修改团长
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/update", "");
            post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.Builder updateBuilder = UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.newBuilder();
            UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder entity = UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
            entity.setAppType(userTeamInfo.getAppType());
            entity.setChannelId(userTeamInfo.getChannelId());
            entity.setChannelUserId(userTeamInfo.getChannelUserId());
            entity.setRealName(userTeamInfo.getRealName());
            updateBuilder.setUpdateRequest(entity.build());
            DataUtils.logBuilder(updateBuilder, "修改团长信息(幂等)_");


            post.setEntity(new ByteArrayEntity(updateBuilder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);

            Assert.assertEquals(200, response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.ResponseCode respa = UserTeamInfoServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
            System.out.println(jsonFormat.printToString(respa));
            DataUtils.logResponse(jsonFormat.printToString(respa));
            CheckDatabase.CheckDatabaseUserTeamInfo(teamRealtionInfoMapper, "teamUpdate", userTeamInfo);

            //3.根据批量channelUserId查询团长信息(幂等)
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/query/batch", "");
            post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoQueryBatchRequest.Builder queryBatchBuilder = UserTeamInfoServiceProto.UserTeamInfoQueryBatchRequest.newBuilder();
            queryBatchBuilder.setAppType(userTeamInfo.getAppType());
            queryBatchBuilder.setChannelId(userTeamInfo.getChannelId());
            queryBatchBuilder.addChannelUserId(userTeamInfo.getChannelUserId());
            DataUtils.logBuilder(queryBatchBuilder, "根据批量channelUserId查询团长信息(幂等)_");

            post.setEntity(new ByteArrayEntity(queryBatchBuilder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);

            Assert.assertEquals(200, response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.UserTeamInfoQueryBatchResponse respb = UserTeamInfoServiceProto.UserTeamInfoQueryBatchResponse.parseFrom(response.getEntity().getContent());
            System.out.println("result:" + jsonFormat.printToString(respb));
            DataUtils.logResponse(jsonFormat.printToString(respb));


            //4.查询粉丝团长
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/query", "");
            HttpPost post = new HttpPost(uri);
            UserTeamInfoServiceProto.FansTeamInfoQueryRequest.Builder builder = UserTeamInfoServiceProto.FansTeamInfoQueryRequest.newBuilder();
            UserTeamInfoServiceProto.UserTeamInfoCommonRequest.Builder commonRequest = UserTeamInfoServiceProto.UserTeamInfoCommonRequest.newBuilder();
            commonRequest.setAppType(userTeamInfo.getAppType());
            commonRequest.setChannelId(userTeamInfo.getChannelId());
            commonRequest.setChannelUserId(userTeamInfo.getChannelUserId());
            builder.setCommonRequest(commonRequest);
            DataUtils.logBuilder(builder, "查询粉丝团长(幂等)_");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            jsonFormat = new JsonFormat();

            Assert.assertEquals(200, response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.FansTeamInfoQueryResponse respc = UserTeamInfoServiceProto.FansTeamInfoQueryResponse.parseFrom(response.getEntity().getContent());
            System.out.println("result:" + jsonFormat.printToString(respc));
            DataUtils.logResponse(jsonFormat.printToString(respc));

            //5.删除丝团长
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/delete", "");
            post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoDeleteRequest.Builder builderDelete = UserTeamInfoServiceProto.UserTeamInfoDeleteRequest.newBuilder();
            UserTeamInfoServiceProto.UserTeamInfoCommonRequest.Builder commonRequest1 = UserTeamInfoServiceProto.UserTeamInfoCommonRequest.newBuilder();
            commonRequest1.setAppType(userTeamInfo.getAppType());
            commonRequest1.setChannelId(userTeamInfo.getChannelId());
            commonRequest1.setChannelUserId(userTeamInfo.getChannelUserId());
            builderDelete.setDeleteRequest(commonRequest);
            DataUtils.logBuilder(builder, "删除团长信息(幂等)_");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            jsonFormat = new JsonFormat();

            Assert.assertEquals(200, response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.ResponseCode respd = UserTeamInfoServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
            System.out.println("result:" + jsonFormat.printToString(respd));
            DataUtils.logResponse(jsonFormat.printToString(respd));
            CheckDatabase.CheckDatabaseUserTeamInfo(teamRealtionInfoMapper, "teamDelete", userTeamInfo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void afterTest() throws IOException {httpClient.close(); }
}
