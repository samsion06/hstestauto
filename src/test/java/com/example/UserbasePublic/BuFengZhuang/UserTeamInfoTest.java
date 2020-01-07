package com.example.UserbasePublic.BuFengZhuang;
import com.example.utils.DataUtils;
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
    private static String channelUserId;
    private static Integer appType=1;
    private static CloseableHttpClient httpClient;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;
    private static JsonFormat JsonFormat;


    @BeforeTest
    public void beforeTest(){
        httpClient = HttpClients.createDefault();
        JsonFormat =new JsonFormat();
        channelUserId=String.valueOf((int)((Math.random()*9+1)*1000));

    }

    @Test(description ="注册团长信息(幂等)")
    public void userTeamInfoRegisterChannelUserIdTest() {
        try {
            //注册团长
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/register", "");
            post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
            builder.setAppType(appType);
            builder.setRealName("周雄鑫");
            builder.setChannelId(channelId);
            builder.setChannelUserId(channelUserId);

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
            entity.setAppType(appType);
            entity.setChannelId(channelId);
            entity.setChannelUserId(channelUserId);
            entity.setRealName("xiongxinzhou");
            builder.setUpdateRequest(entity.build());
            DataUtils.logBuilder(builder,"修改团长信息(幂等)_");

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
            builder.setAppType(appType);
            builder.setChannelId(channelId);
            builder.addChannelUserId(channelUserId);
            DataUtils.logBuilder(builder,"根据批量channelUserId查询团长信息(幂等)_");

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

    @Test(description ="查询粉丝团长(幂等)")
    public void fansTeamInfoQueryChannelUserIdTest(){
        try {
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/query", "");
            HttpPost post = new HttpPost(uri);
            UserTeamInfoServiceProto.FansTeamInfoQueryRequest.Builder builder = UserTeamInfoServiceProto.FansTeamInfoQueryRequest.newBuilder();
            UserTeamInfoServiceProto.UserTeamInfoCommonRequest.Builder commonRequest = UserTeamInfoServiceProto.UserTeamInfoCommonRequest.newBuilder();
            commonRequest.setAppType(appType);
            commonRequest.setChannelId(channelId);
            commonRequest.setChannelUserId(channelUserId);
            builder.setCommonRequest(commonRequest);
            DataUtils.logBuilder(builder,"查询粉丝团长(幂等)_");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            JsonFormat =new JsonFormat();

            Assert.assertEquals(200,response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.FansTeamInfoQueryResponse resp = UserTeamInfoServiceProto.FansTeamInfoQueryResponse.parseFrom(response.getEntity().getContent());
            System.out.println("result:" + JsonFormat.printToString(resp));
            Reporter.log(JsonFormat.printToString(resp));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description ="删除团长信息(幂等)")
    public void fansTeamInfoDeleteAppTypeTest(){
        try {
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/delete", "");
            post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoDeleteRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoDeleteRequest.newBuilder();
            UserTeamInfoServiceProto.UserTeamInfoCommonRequest.Builder commonRequest = UserTeamInfoServiceProto.UserTeamInfoCommonRequest.newBuilder();
            commonRequest.setAppType(appType);
            commonRequest.setChannelId(channelId);
            commonRequest.setChannelUserId(channelUserId);
            builder.setDeleteRequest(commonRequest);
            DataUtils.logBuilder(builder,"删除团长信息(幂等)_");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            JsonFormat =new JsonFormat();

            Assert.assertEquals(200,response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.ResponseCode resp = UserTeamInfoServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
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
            //1.注册团长
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/register", "");
            post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder registerBuilder = UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
            registerBuilder.setAppType(appType);
            registerBuilder.setRealName("周雄鑫");
            registerBuilder.setChannelId(channelId);
            registerBuilder.setChannelUserId(channelUserId);

            post.setEntity(new ByteArrayEntity(registerBuilder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);

            Assert.assertEquals(200,response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.UserTeamInfoRegisterResponse resp = UserTeamInfoServiceProto.UserTeamInfoRegisterResponse.parseFrom(response.getEntity().getContent());
            System.out.println(JsonFormat.printToString(resp));
            Reporter.log(JsonFormat.printToString(resp));

            //2.修改团长
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/update", "");
            post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.Builder updateBuilder = UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.newBuilder();
            UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder entity =UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
            entity.setAppType(appType);
            entity.setChannelId(channelId);
            entity.setChannelUserId(channelUserId);
            entity.setRealName("xiongxinzhou");
            updateBuilder.setUpdateRequest(entity.build());
            DataUtils.logBuilder(updateBuilder,"修改团长信息(幂等)_");

            post.setEntity(new ByteArrayEntity(updateBuilder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);

            Assert.assertEquals(200,response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.ResponseCode respa = UserTeamInfoServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
            System.out.println(JsonFormat.printToString(respa));
            Reporter.log(JsonFormat.printToString(respa));

            //3.根据批量channelUserId查询团长信息(幂等)
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/query/batch", "");
            post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoQueryBatchRequest.Builder queryBatchBuilder = UserTeamInfoServiceProto.UserTeamInfoQueryBatchRequest.newBuilder();
            queryBatchBuilder.setAppType(appType);
            queryBatchBuilder.setChannelId(channelId);
            queryBatchBuilder.addChannelUserId(channelUserId);
            DataUtils.logBuilder(queryBatchBuilder,"根据批量channelUserId查询团长信息(幂等)_");

            post.setEntity(new ByteArrayEntity(queryBatchBuilder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);

            Assert.assertEquals(200,response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.UserTeamInfoQueryBatchResponse respb = UserTeamInfoServiceProto.UserTeamInfoQueryBatchResponse.parseFrom(response.getEntity().getContent());
            System.out.println("result:" + JsonFormat.printToString(respb));
            Reporter.log(JsonFormat.printToString(respb));

            //4.查询粉丝团长
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/query", "");
            HttpPost post = new HttpPost(uri);
            UserTeamInfoServiceProto.FansTeamInfoQueryRequest.Builder builder = UserTeamInfoServiceProto.FansTeamInfoQueryRequest.newBuilder();
            UserTeamInfoServiceProto.UserTeamInfoCommonRequest.Builder commonRequest = UserTeamInfoServiceProto.UserTeamInfoCommonRequest.newBuilder();
            commonRequest.setAppType(appType);
            commonRequest.setChannelId(channelId);
            commonRequest.setChannelUserId(channelUserId);
            builder.setCommonRequest(commonRequest);
            DataUtils.logBuilder(builder,"查询粉丝团长(幂等)_");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            JsonFormat =new JsonFormat();

            Assert.assertEquals(200,response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.FansTeamInfoQueryResponse respc = UserTeamInfoServiceProto.FansTeamInfoQueryResponse.parseFrom(response.getEntity().getContent());
            System.out.println("result:" + JsonFormat.printToString(respc));
            Reporter.log(JsonFormat.printToString(respc));

            //5.删除丝团长
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/info/delete", "");
            post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoDeleteRequest.Builder builderDelete = UserTeamInfoServiceProto.UserTeamInfoDeleteRequest.newBuilder();
            UserTeamInfoServiceProto.UserTeamInfoCommonRequest.Builder commonRequest1 = UserTeamInfoServiceProto.UserTeamInfoCommonRequest.newBuilder();
            commonRequest.setAppType(appType);
            commonRequest.setChannelId(channelId);
            commonRequest.setChannelUserId(channelUserId);
            builderDelete.setDeleteRequest(commonRequest);
            DataUtils.logBuilder(builder,"删除团长信息(幂等)_");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            JsonFormat =new JsonFormat();

            Assert.assertEquals(200,response.getStatusLine().getStatusCode());
            UserTeamInfoServiceProto.ResponseCode respd = UserTeamInfoServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
            System.out.println("result:" + JsonFormat.printToString(respd));
            Reporter.log(JsonFormat.printToString(respd));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }















    @AfterTest
    public void afterTest() throws IOException {httpClient.close(); }
}
