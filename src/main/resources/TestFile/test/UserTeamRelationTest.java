package com.hs.user.base;

import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.UserTeamRelationServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

public class UserTeamRelationTest {
    private CloseableHttpClient httpClient;
    private static final String SCHEME = "http";
    private static final String HOST = "127.0.0.1";
    private static final int POST = 8080;
   /*private static final String HOST = "172.18.0.112";
    private static final int POST = 8080;*/
    
    
    
    @Test
    public void userTeamRelationRegisterAppTypeTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/relation/register", "", null);
            HttpPost post = new HttpPost(uri);
            UserTeamRelationServiceProto.UserTeamRelationRegisterRequest.Builder builder = UserTeamRelationServiceProto.UserTeamRelationRegisterRequest.newBuilder();
            builder.setAppType(0);
            builder.setChannelId(1);
            builder.setChannelUserId("99888444");
            builder.setTeamUserId("3213213213312");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserTeamRelationServiceProto.UserTeamRelationRegisterResponse resp = UserTeamRelationServiceProto.UserTeamRelationRegisterResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void userTeamRelationRegisterChannelIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/relation/register", "", null);
            HttpPost post = new HttpPost(uri);
            UserTeamRelationServiceProto.UserTeamRelationRegisterRequest.Builder builder = UserTeamRelationServiceProto.UserTeamRelationRegisterRequest.newBuilder();
            builder.setAppType(1);
            builder.setChannelId(0);
            builder.setChannelUserId("99888444");
            builder.setTeamUserId("3213213213312");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserTeamRelationServiceProto.UserTeamRelationRegisterResponse resp = UserTeamRelationServiceProto.UserTeamRelationRegisterResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void userTeamRelationRegisterChannelUserIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/relation/register", "", null);
            HttpPost post = new HttpPost(uri);
            UserTeamRelationServiceProto.UserTeamRelationRegisterRequest.Builder builder = UserTeamRelationServiceProto.UserTeamRelationRegisterRequest.newBuilder();
            builder.setAppType(1);
            builder.setChannelId(1);
            builder.setChannelUserId("");
            builder.setTeamUserId("3213213213312");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserTeamRelationServiceProto.UserTeamRelationRegisterResponse resp = UserTeamRelationServiceProto.UserTeamRelationRegisterResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    @Test
    public void userTeamRelationRegisterTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/relation/register", "", null);
            HttpPost post = new HttpPost(uri);
            UserTeamRelationServiceProto.UserTeamRelationRegisterRequest.Builder builder = UserTeamRelationServiceProto.UserTeamRelationRegisterRequest.newBuilder();
            builder.setAppType(1);
            builder.setChannelId(1);
            builder.setChannelUserId("998884446");
            builder.setTeamUserId("2234235331111");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserTeamRelationServiceProto.UserTeamRelationRegisterResponse resp = UserTeamRelationServiceProto.UserTeamRelationRegisterResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void userTeamRelationUntyingAppTypeTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/relation/delete", "", null);
            HttpPost post = new HttpPost(uri);
            UserTeamRelationServiceProto.UserTeamRelationUntyingRequest.Builder builder = UserTeamRelationServiceProto.UserTeamRelationUntyingRequest.newBuilder();
            UserTeamRelationServiceProto.UserTeamRelationCommonRequest.Builder commonRequest = UserTeamRelationServiceProto.UserTeamRelationCommonRequest.newBuilder();
            commonRequest.setAppType(0);
            commonRequest.setChannelId(1);
            commonRequest.setChannelUserId("99888222");
            builder.setCommonRequest(commonRequest);
            builder.setTeamUserId("321321321331");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserTeamRelationServiceProto.ResponseCode resp = UserTeamRelationServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void userTeamRelationUntyingChannelIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/relation/delete", "", null);
            HttpPost post = new HttpPost(uri);
            UserTeamRelationServiceProto.UserTeamRelationUntyingRequest.Builder builder = UserTeamRelationServiceProto.UserTeamRelationUntyingRequest.newBuilder();
            UserTeamRelationServiceProto.UserTeamRelationCommonRequest.Builder commonRequest = UserTeamRelationServiceProto.UserTeamRelationCommonRequest.newBuilder();
            commonRequest.setAppType(1);
            commonRequest.setChannelId(0);
            commonRequest.setChannelUserId("99888222");
            builder.setCommonRequest(commonRequest);
            builder.setTeamUserId("3213213213312");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserTeamRelationServiceProto.ResponseCode resp = UserTeamRelationServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void userTeamRelationUntyingChannelUserIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/relation/delete", "", null);
            HttpPost post = new HttpPost(uri);
            UserTeamRelationServiceProto.UserTeamRelationUntyingRequest.Builder builder = UserTeamRelationServiceProto.UserTeamRelationUntyingRequest.newBuilder();
            UserTeamRelationServiceProto.UserTeamRelationCommonRequest.Builder commonRequest = UserTeamRelationServiceProto.UserTeamRelationCommonRequest.newBuilder();
            commonRequest.setAppType(1);
            commonRequest.setChannelId(1);
            commonRequest.setChannelUserId("");
            builder.setCommonRequest(commonRequest);
            builder.setTeamUserId("3213213213312");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserTeamRelationServiceProto.ResponseCode resp = UserTeamRelationServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void userTeamRelationUntyingTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/relation/delete", "", null);
            HttpPost post = new HttpPost(uri);
            UserTeamRelationServiceProto.UserTeamRelationUntyingRequest.Builder builder = UserTeamRelationServiceProto.UserTeamRelationUntyingRequest.newBuilder();
            UserTeamRelationServiceProto.UserTeamRelationCommonRequest.Builder commonRequest = UserTeamRelationServiceProto.UserTeamRelationCommonRequest.newBuilder();
            commonRequest.setAppType(1);
            commonRequest.setChannelId(1);
            commonRequest.setChannelUserId("99888222");
            builder.setCommonRequest(commonRequest);
            builder.setTeamUserId("3213213213312");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserTeamRelationServiceProto.ResponseCode resp = UserTeamRelationServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    @Before
    public void before(){
        httpClient = HttpClients.createDefault();
    }

    @After
    public void after() throws IOException {
        httpClient.close();
    }
}
