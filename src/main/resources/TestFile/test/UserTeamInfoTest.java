package com.hs.user.base;

import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.UserTeamInfoServiceProto;
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

public class UserTeamInfoTest {
    private CloseableHttpClient httpClient;
    private static final String SCHEME = "http";
    private static final String HOST = "127.0.0.1";
    private static final int POST = 8080;
   /*private static final String HOST = "172.18.0.112";
    private static final int POST = 8080;*/
   @Test
   public void userTeamInfoRegisterChannelUserIdTest(){
	   try {
		   URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/register", "", null);
		   HttpPost post = new HttpPost(uri);
		   UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
		   builder.setAppType(1);
		   builder.setRealName("细密是");
		   builder.setChannelId(1);
		   builder.setChannelUserId("");
		   post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
		   post.setHeader("Content-Type", "application/x-protobuf");
		
		   HttpResponse response = httpClient.execute(post);
		   JsonFormat jsonFormat =new JsonFormat();
		   if (response.getStatusLine().getStatusCode() == 200) {
			   UserTeamInfoServiceProto.UserTeamInfoRegisterResponse resp = UserTeamInfoServiceProto.UserTeamInfoRegisterResponse.parseFrom(response.getEntity().getContent());
			   System.out.println("result:" + jsonFormat.printToString(resp));
		   } else {
			   System.out.println(response.getStatusLine().getStatusCode());
		   }
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
   }
	
	@Test
	public void userTeamInfoRegisterChannelIdTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/register", "", null);
			HttpPost post = new HttpPost(uri);
			UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
			builder.setAppType(1);
			builder.setRealName("细密是");
			builder.setChannelId(0);
			builder.setChannelUserId("112244395");
			post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
			post.setHeader("Content-Type", "application/x-protobuf");
			
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserTeamInfoServiceProto.UserTeamInfoRegisterResponse resp = UserTeamInfoServiceProto.UserTeamInfoRegisterResponse.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void userTeamInfoRegisterAppTypeTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/register", "", null);
			HttpPost post = new HttpPost(uri);
			UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
			builder.setAppType(0);
			builder.setRealName("细密是");
			builder.setChannelId(1);
			builder.setChannelUserId("112244395");
			post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
			post.setHeader("Content-Type", "application/x-protobuf");
			
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserTeamInfoServiceProto.UserTeamInfoRegisterResponse resp = UserTeamInfoServiceProto.UserTeamInfoRegisterResponse.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void userTeamInfoRegisterRealNameTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/register", "", null);
			HttpPost post = new HttpPost(uri);
			UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
			builder.setAppType(1);
			builder.setRealName("");
			builder.setChannelId(1);
			builder.setChannelUserId("112244395");
			post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
			post.setHeader("Content-Type", "application/x-protobuf");
			
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserTeamInfoServiceProto.UserTeamInfoRegisterResponse resp = UserTeamInfoServiceProto.UserTeamInfoRegisterResponse.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
    public void userTeamInfoRegisterTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/register", "", null);
            HttpPost post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
	        UserTeamInfoServiceProto.UserTeamAddressRegister.Builder address =UserTeamInfoServiceProto.UserTeamAddressRegister.newBuilder();
            builder.setAppType(1);
            builder.setAuditorName("细密是");
            builder.setChannelId(1);
            builder.setChannelUserId("9384439");
            builder.setCompanyName("小米");
            builder.setDeposit(299229999);
            builder.setEmergencyNumber("135879998778");
            builder.setGender(1);
            builder.setHeadNum("1928283833");
            builder.setIsShowCommission(1);
            builder.setIsVirtual(1);
            builder.setLicenseImg("/sd/fs/d/fsdf/sd/f");
            builder.setLicenseNumber("2121321e21321");
            builder.setMobile("5666623231231");
            builder.setOperatorId("123213");
            builder.setOperatorLongId(123213);
            builder.setOperatorTel("dfsdfwefsd");
            builder.setRealName("李白");
            builder.setRecommend("23213123");
            builder.setSource(2);
            builder.setStatus(1);
           
            builder.setStopReason("销售小能手");
            builder.setCompanyId(22);
            builder.setWeixin("213123213");
	
	       /* address.setAdcode(213213);
	        address.setAddress("sdfsfsdf");
	        address.setAddressDetail("sdfsdf");
	        address.setAppType(2);
	        address.setChannelId(2);
	        address.setChannelUserId(builder.getChannelUserId());
	        address.setCity("广州");
	        address.setCityCode(020);
	        address.setCommunity("二沙岛");
	        address.setDistrict("二沙岛");
	        address.setDistrictCode(001);
	        address.setGeoHash("0.3430423");
	        address.setGeoHash5Km("0.33334");
	        address.setLatitude(102.023213);
	        address.setLongitude(1223.534324);
	        address.setMemberNumber(123123123);
	        address.setProvince("广东省");
	        address.setProvinceCode(404040404);*/
         
	        builder.setAddress(address);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserTeamInfoServiceProto.UserTeamInfoRegisterResponse resp = UserTeamInfoServiceProto.UserTeamInfoRegisterResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	
	@Test
	public void userTeamInfoUpdateChannelUserIdTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/update", "", null);
			HttpPost post = new HttpPost(uri);
			UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.newBuilder();
			UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder entity =UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
			entity.setAppType(1);
			entity.setChannelId(1);
			entity.setChannelUserId("");
			builder.setUpdateRequest(entity.build());
			post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
			post.setHeader("Content-Type", "application/x-protobuf");
			
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserTeamInfoServiceProto.ResponseCode resp = UserTeamInfoServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void userTeamInfoUpdateAppTypeTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/update", "", null);
			HttpPost post = new HttpPost(uri);
			UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.newBuilder();
			UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder entity =UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
			entity.setAppType(0);
			entity.setChannelId(1);
			entity.setChannelUserId("3213213213312");
			builder.setUpdateRequest(entity.build());
			post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
			post.setHeader("Content-Type", "application/x-protobuf");
			
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserTeamInfoServiceProto.ResponseCode resp = UserTeamInfoServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void userTeamInfoUpdateChannelIdTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/update", "", null);
			HttpPost post = new HttpPost(uri);
			UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.newBuilder();
			UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder entity =UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
			entity.setAppType(1);
			entity.setChannelId(0);
			entity.setChannelUserId("3213213213312");
			builder.setUpdateRequest(entity.build());
			post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
			post.setHeader("Content-Type", "application/x-protobuf");
			
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserTeamInfoServiceProto.ResponseCode resp = UserTeamInfoServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    @Test
    public void userTeamInfoUpdateTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoUpdateRequest.newBuilder();
            UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.Builder entity =UserTeamInfoServiceProto.UserTeamInfoRegisterRequest.newBuilder();
            entity.setAppType(1);
            entity.setAuditorName("细密是-2");
            entity.setChannelId(1);
            entity.setChannelUserId("3213213213312");
            entity.setCompanyName("小米-2");
            entity.setDeposit(9999999);
            entity.setEmergencyNumber("145879998778");
            entity.setGender(2);
            entity.setHeadNum("787654333");
            entity.setIsShowCommission(2);
            entity.setIsVirtual(2);
            entity.setLicenseImg("/gd/wewegsdgsd/gsdg");
            entity.setLicenseNumber("2121321e21321");
            entity.setMobile("780883343");
            entity.setOperatorId("55634534");
            entity.setOperatorLongId(23346);
            entity.setOperatorTel("hhhhhjjjj");
            entity.setRealName("李白-2");
            entity.setRecommend("456457567");
            entity.setSource(3);
            entity.setStatus(2);
            
            entity.setStopReason("销售小能手-2");
            entity.setCompanyId(2009);
            entity.setWeixin("090908");
            builder.setUpdateRequest(entity.build());
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserTeamInfoServiceProto.ResponseCode resp = UserTeamInfoServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	@Test
	public void fansTeamInfoQueryChannelUserIdTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/query", "", null);
			HttpPost post = new HttpPost(uri);
			UserTeamInfoServiceProto.FansTeamInfoQueryRequest.Builder builder = UserTeamInfoServiceProto.FansTeamInfoQueryRequest.newBuilder();
			UserTeamInfoServiceProto.UserTeamInfoCommonRequest.Builder commonRequest = UserTeamInfoServiceProto.UserTeamInfoCommonRequest.newBuilder();
			commonRequest.setAppType(1);
			commonRequest.setChannelId(1);
			commonRequest.setChannelUserId("");
			
			builder.setCommonRequest(commonRequest);
			post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
			post.setHeader("Content-Type", "application/x-protobuf");
			
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserTeamInfoServiceProto.FansTeamInfoQueryResponse resp = UserTeamInfoServiceProto.FansTeamInfoQueryResponse.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void fansTeamInfoQuerytAppTypeTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/query", "", null);
			HttpPost post = new HttpPost(uri);
			UserTeamInfoServiceProto.FansTeamInfoQueryRequest.Builder builder = UserTeamInfoServiceProto.FansTeamInfoQueryRequest.newBuilder();
			UserTeamInfoServiceProto.UserTeamInfoCommonRequest.Builder commonRequest = UserTeamInfoServiceProto.UserTeamInfoCommonRequest.newBuilder();
			commonRequest.setAppType(0);
			commonRequest.setChannelId(1);
			commonRequest.setChannelUserId("99888222");
			
			builder.setCommonRequest(commonRequest);
			post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
			post.setHeader("Content-Type", "application/x-protobuf");
			
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserTeamInfoServiceProto.FansTeamInfoQueryResponse resp = UserTeamInfoServiceProto.FansTeamInfoQueryResponse.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void fansTeamInfoQuerytChannelIdTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/query", "", null);
			HttpPost post = new HttpPost(uri);
			UserTeamInfoServiceProto.FansTeamInfoQueryRequest.Builder builder = UserTeamInfoServiceProto.FansTeamInfoQueryRequest.newBuilder();
			UserTeamInfoServiceProto.UserTeamInfoCommonRequest.Builder commonRequest = UserTeamInfoServiceProto.UserTeamInfoCommonRequest.newBuilder();
			commonRequest.setAppType(1);
			commonRequest.setChannelId(0);
			commonRequest.setChannelUserId("99888222");
			
			builder.setCommonRequest(commonRequest);
			post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
			post.setHeader("Content-Type", "application/x-protobuf");
			
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserTeamInfoServiceProto.FansTeamInfoQueryResponse resp = UserTeamInfoServiceProto.FansTeamInfoQueryResponse.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    @Test
    public void fansTeamInfoQueryTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/query", "", null);
            HttpPost post = new HttpPost(uri);
            UserTeamInfoServiceProto.FansTeamInfoQueryRequest.Builder builder = UserTeamInfoServiceProto.FansTeamInfoQueryRequest.newBuilder();
            UserTeamInfoServiceProto.UserTeamInfoCommonRequest.Builder commonRequest = UserTeamInfoServiceProto.UserTeamInfoCommonRequest.newBuilder();
            commonRequest.setAppType(1);
            commonRequest.setChannelId(1);
            commonRequest.setChannelUserId("99888222");
           
            builder.setCommonRequest(commonRequest);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserTeamInfoServiceProto.FansTeamInfoQueryResponse resp = UserTeamInfoServiceProto.FansTeamInfoQueryResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	
	@Test
	public void fansTeamInfoQueryBatchAppTypeTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/query/batch", "", null);
			HttpPost post = new HttpPost(uri);
			UserTeamInfoServiceProto.UserTeamInfoQueryBatchRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoQueryBatchRequest.newBuilder();
			builder.setAppType(0);
			builder.setChannelId(1);
			builder.addChannelUserId("3213213213312");
			builder.addChannelUserId("112244395");
			post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
			post.setHeader("Content-Type", "application/x-protobuf");
			
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserTeamInfoServiceProto.UserTeamInfoQueryBatchResponse resp = UserTeamInfoServiceProto.UserTeamInfoQueryBatchResponse.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void fansTeamInfoQueryBatchChannelUserIdTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/query/batch", "", null);
			HttpPost post = new HttpPost(uri);
			UserTeamInfoServiceProto.UserTeamInfoQueryBatchRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoQueryBatchRequest.newBuilder();
			builder.setAppType(1);
			builder.setChannelId(1);
			post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
			post.setHeader("Content-Type", "application/x-protobuf");
			
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserTeamInfoServiceProto.UserTeamInfoQueryBatchResponse resp = UserTeamInfoServiceProto.UserTeamInfoQueryBatchResponse.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void fansTeamInfoQueryBatchTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/query/batch", "", null);
			HttpPost post = new HttpPost(uri);
			UserTeamInfoServiceProto.UserTeamInfoQueryBatchRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoQueryBatchRequest.newBuilder();
			builder.setAppType(1);
			builder.setChannelId(1);
			builder.addChannelUserId("3213213213312");
			builder.addChannelUserId("112244395");
			post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
			post.setHeader("Content-Type", "application/x-protobuf");
			
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserTeamInfoServiceProto.UserTeamInfoQueryBatchResponse resp = UserTeamInfoServiceProto.UserTeamInfoQueryBatchResponse.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void fansTeamInfoDeleteAppTypeTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/delete", "", null);
			HttpPost post = new HttpPost(uri);
			UserTeamInfoServiceProto.UserTeamInfoDeleteRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoDeleteRequest.newBuilder();
			UserTeamInfoServiceProto.UserTeamInfoCommonRequest.Builder commonRequest = UserTeamInfoServiceProto.UserTeamInfoCommonRequest.newBuilder();
			commonRequest.setAppType(0);
			commonRequest.setChannelId(1);
			commonRequest.setChannelUserId("3213213213312");
			
			builder.setDeleteRequest(commonRequest);
			post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
			post.setHeader("Content-Type", "application/x-protobuf");
			
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserTeamInfoServiceProto.ResponseCode resp = UserTeamInfoServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void fansTeamInfoDeleteChannelIdTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/delete", "", null);
			HttpPost post = new HttpPost(uri);
			UserTeamInfoServiceProto.UserTeamInfoDeleteRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoDeleteRequest.newBuilder();
			UserTeamInfoServiceProto.UserTeamInfoCommonRequest.Builder commonRequest = UserTeamInfoServiceProto.UserTeamInfoCommonRequest.newBuilder();
			commonRequest.setAppType(1);
			commonRequest.setChannelId(0);
			commonRequest.setChannelUserId("3213213213312");
			
			builder.setDeleteRequest(commonRequest);
			post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
			post.setHeader("Content-Type", "application/x-protobuf");
			
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserTeamInfoServiceProto.ResponseCode resp = UserTeamInfoServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void fansTeamInfoDeleteChannelUserIdTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/delete", "", null);
			HttpPost post = new HttpPost(uri);
			UserTeamInfoServiceProto.UserTeamInfoDeleteRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoDeleteRequest.newBuilder();
			UserTeamInfoServiceProto.UserTeamInfoCommonRequest.Builder commonRequest = UserTeamInfoServiceProto.UserTeamInfoCommonRequest.newBuilder();
			commonRequest.setAppType(1);
			commonRequest.setChannelId(1);
			commonRequest.setChannelUserId("");
			
			builder.setDeleteRequest(commonRequest);
			post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
			post.setHeader("Content-Type", "application/x-protobuf");
			
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserTeamInfoServiceProto.ResponseCode resp = UserTeamInfoServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void fansTeamInfoDeleteTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/team/info/delete", "", null);
			HttpPost post = new HttpPost(uri);
			UserTeamInfoServiceProto.UserTeamInfoDeleteRequest.Builder builder = UserTeamInfoServiceProto.UserTeamInfoDeleteRequest.newBuilder();
			UserTeamInfoServiceProto.UserTeamInfoCommonRequest.Builder commonRequest = UserTeamInfoServiceProto.UserTeamInfoCommonRequest.newBuilder();
			commonRequest.setAppType(1);
			commonRequest.setChannelId(1);
			commonRequest.setChannelUserId("3213213213312");
			
			builder.setDeleteRequest(commonRequest);
			post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
			post.setHeader("Content-Type", "application/x-protobuf");
			
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserTeamInfoServiceProto.ResponseCode resp = UserTeamInfoServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
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
