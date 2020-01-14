package com.example.Jmeter;
import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.UserTeamInfoServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.io.IOException;
import java.net.URI;

public class TeamInfoUpdate extends AbstractJavaSamplerClient {

    private String ChannelUserId;
    private String realName;
    private String result;
    private String url;
    private Integer port;
    private Integer AppType;

    @Override
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("url", "");
        params.addArgument("port", "");
        params.addArgument("AppType", "");//AppType
        params.addArgument("ChannelUserId", "");
        params.addArgument("realName", "");
        return params;
    }
    @Override
    public void setupTest(JavaSamplerContext arg0) {
        url = arg0.getParameter("url");
        port = arg0.getIntParameter("port");
        AppType = arg0.getIntParameter("AppType");
        ChannelUserId = arg0.getParameter("ChannelUserId");
        realName = arg0.getParameter("realName");
    }

    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post=null;
        URI uri;
        JsonFormat jsonFormat = new JsonFormat();
        SampleResult sr = new SampleResult();
        try {
            //构造请求
            uri = new URI("http", null, url, port, "/aliPay/auth/info", "", null);
            post = new HttpPost(uri);

            UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.Builder updateBuilder = UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.newBuilder();
            UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder entity = UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
            entity.setAppType(AppType);
            entity.setChannelId(1);
            entity.setChannelUserId(ChannelUserId);
            entity.setRealName(realName);
            entity.setStatus(0);
            updateBuilder.setUpdateRequest(entity.build());

            sr.setSamplerData("data:\n"+updateBuilder.toString());
            sr.setDataType(SampleResult.TEXT);
            post.setEntity(new ByteArrayEntity(updateBuilder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            //事务开始计时
            sr.sampleStart();
            //发送请求
            HttpResponse response = httpClient.execute(post);
            //判断响应状态
            if (response.getStatusLine().getStatusCode() == 200) {
                UserTeamInfoServiceProto.ResponseCode resp = UserTeamInfoServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
                //事务计时结束
                sr.sampleEnd();
                sr.setSuccessful(true);
                result = jsonFormat.printToString(resp);
            } else{
                sr.setSuccessful(false);
                result = String.valueOf(response.getStatusLine().getStatusCode());
            }
            sr.setResponseData(result, "utf-8");
            sr.setDataType(SampleResult.TEXT);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(httpClient != null) {
                    httpClient.close();
                }
                if(post != null){
                    post.releaseConnection();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return sr;
    }

    @Override
    public void teardownTest(JavaSamplerContext arg0) {

    }
}
