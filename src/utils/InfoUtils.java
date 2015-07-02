//package utils;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.protocol.ClientContext;
//import org.apache.http.entity.BasicHttpEntity;
//import org.apache.http.impl.client.BasicCookieStore;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.protocol.BasicHttpContext;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONObject;
//
//import com.leo.common.util.MD5Digest;
//import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
//
//public class InfoUtils {
//	
//
//
//	static HttpClient httpclient = new DefaultHttpClient();
//	static BasicCookieStore cookieStore = new BasicCookieStore();
//	static BasicHttpContext httpContext = new BasicHttpContext();
//	static {
//		httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
//	}
//
//	@SuppressWarnings("static-access")
//	public static JSONObject sendRestInter (String url, Map<String, Object> pars) {
//
//		String smsUrl = url;
//		HttpPost httppost = new HttpPost(smsUrl);
//		// pars.put("App-Key", "fybao.superjia.com");
//		// pars.put("App-Secret", "MT0VT5EN1FAP7SGA840OBW2DUFJUAB");
//
//		httppost.addHeader("App-Key", "fybao.superjia.com");
//
//        httppost.addHeader("App_Time", new Date().getTime()+"");
//        
//        httppost.addHeader("ver", "1.1");
//        httppost.addHeader("os", "android"); //鎵嬫満鏉ユ簮 (android  /  ios)
//        httppost.addHeader("imei", "123123123");
//        httppost.addHeader("model", "MiNote");
//        httppost.addHeader("u_ticket", "enlFSXdDSnlWck0lQCU4NjUwMDIwMjMwMTQ0NjclQCUyMzg1NTU3OSVAJTEwMDgwJUAlZmMwYWVmMTc3ZThhMmRlYjlmY2YwZGVlN2RmODFiODE=");
//		String strResult = "";
//
//		try {
//
//			// List<NameValuePair> nameValuePairs = new
//			// ArrayList<NameValuePair>();
//			JSONObject jobj = new JSONObject();
//			if (pars != null) {
//				Iterator<String> iter = pars.keySet().iterator();
//				while (iter.hasNext()) {
//					String key = iter.next();
//					Object value = pars.get(key);
//					jobj.put(key, value);
//				}
//			}
//			
//			List<Map.Entry<String, Object>> params = new ArrayList<Map.Entry<String, Object>>(pars.entrySet());// 
//            Collections.sort(params, new Comparator<Map.Entry<String, Object>>() {
//                public int compare(Entry<String, Object> o1,Entry<String, Object> o2) {
//                    if(o1.getKey()==null || o2.getKey()==null)
//                        return 0;
//                    return o1.getKey().compareTo(o2.getKey());
//                }
//            });
//            
//            //a=1&b=b&MT0VT5EN1FAP7SGA840OBW2DUFJUAB&1100000000
//            String secret = "";
//            for(Map.Entry<String, Object> param : params){
//                String key = param.getKey();
//                Object value = param.getValue();
//                secret = secret + key + "=" + value + "&";
//            }
//            System.out.println(secret);
//			if(secret.endsWith("&"))
//                secret = secret.substring(0, secret.length()-1);
//            
//            long currentTime = System.currentTimeMillis()/1000000;
//            
//            String secret_ = secret + "&" + "MT0VT5EN1FAP7SGA840OBW2DUFJUAB" + "&" + currentTime;
//            //logger.info(secretFirst);
//            String md5 = MD5Digest.getMD5Digest(secret_);
//            System.out.println(secret_);
//            System.out.println(md5);
//            httppost.addHeader("App-Secret", md5);
//            
//			// nameValuePairs.add(new BasicNameValuePair("msg",
//			// getStringFromJson(jobj)));
//			// httppost.addHeader("Content-type",
//			// "application/x-www-form-urlencoded");
//			// httppost.setEntity(new
//			// UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
//			BasicHttpEntity en = new BasicHttpEntity();
//			en.setContentType("application/json");
//			String str = jobj.toString();
//			if (str == null) {
//				str = "";
//			}
//			byte[] bytes = str.getBytes();
//
//			ByteInputStream bis = new ByteInputStream(bytes, bytes.length);
//			en.setContent(bis);
//			httppost.setEntity(en);
//
//			JSONObject sobj = null;
//			HttpResponse response = httpclient.execute(httppost, httpContext);
//			if (response.getStatusLine().getStatusCode() == 200) {
//				sobj = new JSONObject();
//				/* 璇昏繑鍥炴暟鎹� */
//				String conResult = EntityUtils.toString(response.getEntity());
//				sobj = new JSONObject(conResult);
//				strResult = sobj.toString();
//			} else {
//				String err = response.getStatusLine().getStatusCode() + "";
//				strResult += "鍙戦�佸け璐�:" + err;
//			}
//			return sobj;
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return new JSONObject();
//	}
//
//	@SuppressWarnings("unused")
//	private static String getStringFromJson(JSONObject adata) {
//		StringBuffer sb = new StringBuffer();
//		sb.append("{");
////		for (String key : adata.keySet()) {
////			sb.append("\"" + key + "\":\"" + adata.get(key) + "\",");
////		}
//		String rtn = sb.toString().substring(0, sb.toString().length() - 1)
//				+ "}";
//		return rtn;
//	}
//
//	public static void main(String[] args) {
//		try {
//			// sendSMS("13651685162",Global.registBeforeV+"111111"+Global.registAfterV);
//			// sendSMS("13651685162",
//			// "鎴挎簮瀹濅簲鏈堟椿鍔ㄥ紑濮嬪挴锛屽綋鏈堜笂浼犱俊鎭鏍告垚鍔熺疮璁�200鏉″啀濂栦簩鐧撅紝500鏉″涓�鍗冿紝1000鏉″浜屽崈浜旓紝璧跺揩琛屽姩鍚э紒鍔冲姩鏈�鍏夎崳绁濆ぇ瀹惰妭鏃ュ揩涔愶紒銆愭埧婧愬疂銆�");
//		    System.out.println(MD5Digest.getMD5Digest("offset=0&pageSize=10&userId=136&MT0VT5EN1FAP7SGA840OBW2DUFJUAB&1406287"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
