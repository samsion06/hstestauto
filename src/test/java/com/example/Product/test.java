package com.example.Product;

import com.example.utils.HttpConfigUtil;
import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.ResultResponse;
import com.hs.user.base.proto.UserTeamRelationServiceProto;
import com.hs.user.base.proto.UserWeChatAuthServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;

public class test {

    public static void main(String [] args) throws IOException {

            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {
                URI uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.urlyx, "/user/team/relation/register", "");
                System.out.println(uri);
                HttpPost post = new HttpPost(uri);
                UserTeamRelationServiceProto.UserTeamRelationRegisterRequest.Builder builder = UserTeamRelationServiceProto.UserTeamRelationRegisterRequest.newBuilder();
                builder.setAppType(1);
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
}
