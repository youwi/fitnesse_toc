package com.qa.TestHttpClient;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
//import net.sf.json.JSONObject;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpClientUtil {

    private CloseableHttpClient httpclient = null;
    long stime = System.currentTimeMillis();
    private String responseBody = null;
    private HttpResponse response = null;
    private HttpResponse wkssoResponse = null;
    private long responseTime = 999999999;
    int timeout = 5;

    @SuppressWarnings("deprecation")
    public HttpClientUtil() {
        SSLContext ctx = null;  // 4.3
        SSLContext sslContext=null;// 4.4
        try {
            ctx = SSLContext.getInstance("SSL");
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
                    return true;
                }
                public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
                    return true;
                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);

            sslContext = SSLContexts.custom()
                    .loadTrustMaterial(null, new TrustStrategy() {
                        @Override
                        public boolean isTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
                            return true;
                        }
                    })
                    //.useTLS()
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

       // HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();
        CloseableHttpClient client ;//= HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        client = HttpClients.custom()
                //	.setKeepAliveStrategy(myStrategy)
           //     .setSslcontext(sslContext)
                .setSSLSocketFactory(sslsf)
                .setDefaultRequestConfig(config)
        //        .setConnectionManager(connManager)  // bug? not tr ust
                .setDefaultRequestConfig(config)
                .setConnectionManagerShared(true)
                .build();

        this.httpclient = client;
        //httpClientBuilder.setSSLContext(ctx);
        //httpClientBuilder.setSslcontext(ctx);//not work on 4.4
        //this.httpclient = httpClientBuilder.build();
        //this.httpclient = HttpClients.createDefault();
    }

    public String httpPostRequest(String URL, HttpRequestCallback ci) throws IOException {
        try {
            Iterator<Map.Entry<String, String>> iter = ci.getHeaderParameters();
            HttpPost httpPost = new HttpPost(URL);
            int flag = 0;
            int CTFlag = 0;
            while (iter.hasNext()) {
                Map.Entry<String, String> me = iter.next();
                httpPost.addHeader(me.getKey(), me.getValue());
                System.out.println("请求头：  " + me.getKey() + ":" + me.getValue());
                if ("os".equals(me.getKey())) {
                    flag = 1;
                }
                if ("Content-Type".equals(me.getKey())) {
                    CTFlag = 1;
                    String vv=me.getValue();
                    if(vv!=null && vv.contains("application/json")){
                        CTFlag=2;// JSON
                    }
                    if(vv!=null && vv.contains("application/x-www-form-urlencoded")){
                        CTFlag=3;// form
                    }
                }
            }
            if (0 == flag) {
                httpPost.addHeader("os", "monitor");
            }
            if (0 == CTFlag) {
                httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
            }
            System.out.println("请求地址：  POST  " + httpPost.getURI());

            if(  (3==CTFlag)  && ( null != ci.getParam()) ){
                System.out.println("请求参数：  " + ci.getParam());
                httpPost.setEntity(new StringEntity(ci.getParam()));
            }

            if ( (2==CTFlag) && (null != ci.getJsonParam())) {
                System.out.println("请求参数：  " + ci.getJsonParam());
                httpPost.setEntity(new StringEntity(ci.getJsonParam()));
            }

            // Before end
//            ResponseHandler<String> responseHandler = createResponseHandler();
            httpPost.setConfig(RequestConfig.custom().setRedirectsEnabled(ci.getIsRedirect()).build());
            stime = System.currentTimeMillis();
            response = httpclient.execute(httpPost);
        //    List<Cookie> cookies = ((AbstractHttpClient) httpclient).getCookieStore().getCookies();
            printState(response,ci);

            return responseBody;
        } finally {
            httpclient.close();
        }
    }
    public void printState(HttpResponse response,HttpRequestCallback ci) throws IOException {
        int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            setResponseBody(EntityUtils.toString(entity));
            responseTime = System.currentTimeMillis() - stime;
            if(response.getFirstHeader("Content-Type").getValue().contains("text/html")){
                System.out.println("返回:<HTML>" );
            }else{
                System.out.println("完整响应体： " + responseBody);
            }

        } else if(status==302) {
            System.out.println("返回:<中间状态:302,自动重定向:"+ci.getIsRedirect()+">");
        }else{
            System.out.println("响应时间： "+responseTime+"ms" );
            System.out.println("-------------------------------------------");
            throw new ClientProtocolException("Unexpected response status(未处理状态码): " + status);
        }
        System.out.println("响应时间： "+responseTime+"ms" );
        System.out.println("-------------------------------------------");
    }

    /**
     * @param URL
     * @param ci 反向取值方法
     * @return
     * @throws IOException
     */
    public String httpGetRequest(String URL, HttpRequestCallback ci)
            throws IOException {
        try {
            Iterator<Map.Entry<String, String>> iter = ci.getHeaderParameters();
            HttpGet httpGet = new HttpGet(URL);

            int flag = 0;
            while (iter.hasNext()) {
                Map.Entry<String, String> me = iter.next();
                httpGet.addHeader(me.getKey(), me.getValue());
                System.out.println("请求头：  " + me.getKey() + ":" + me.getValue());
                if ("os".equals(me.getKey())) {
                    flag = 1;
                }
            }
            if (0 == flag) {
                httpGet.addHeader("os", "monitor");
            }
            System.out.println("GET 请求地址：  " + httpGet.getURI());

//            ResponseHandler<String> responseHandler = createResponseHandler();
            httpGet.setConfig(RequestConfig.custom().setRedirectsEnabled(ci.getIsRedirect()).build());
            stime = System.currentTimeMillis();
            response = httpclient.execute(httpGet);

            printState(response,ci);
            return responseBody;
        } finally {
            httpclient.close();
        }
    }


//    ResponseHandler createResponseHandler() {
//        return new ResponseHandler<String>() {
//            public String handleResponse(final HttpResponse response)
//                    throws ClientProtocolException, IOException {
//                int status = response.getStatusLine().getStatusCode();
//                if (status >= 200 && status < 300) {
//                    HttpEntity entity = response.getEntity();
//                    return entity != null ? EntityUtils.toString(entity)
//                            : null;
//                } else {
//                    throw new ClientProtocolException(
//                            "Unexpected response status: " + status);
//                }
//            }
//        };
//    }

    public String httpRequest(String URL, HttpRequestCallback ci, String type) throws IOException {
        if ("get".equals(type.toLowerCase())) {
            return httpGetRequest(URL, ci);
        } else if ("post".equals(type.toLowerCase())) {
            return httpPostRequest(URL, ci);
        } else {
            return null;
        }
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public Header[] getResponseHeader(String responseHeaderKey) {
        Header[] headers = response.getHeaders(responseHeaderKey);
        return headers;
    }

//    public Header[] getwkssoResponseHeader(String responseHeaderKey) {
//        Header[] headers = wkssoResponse.getHeaders(responseHeaderKey);
//        return headers;
//    }

    public long getResponseTime() {
        return responseTime;
    }
}