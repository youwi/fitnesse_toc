package com.qa;

import com.qa.constants.ConfigConstantsTest;
import com.qa.utils.Data;
import org.apache.http.Header;

import java.io.IOException;

/**
 * Created by Gavin-pc on 2016/12/19.
 */
public class SSOLogin  extends BaseServer{
    public String JSESSIONID = null;

    public SSOLogin(String URL, String env, String type) {
        super(URL, env, type);
    }

    public String firstLoginRun(String fullurl, Data indata) throws IOException {
        String responseBody = requestForExecution(fullurl,indata);
        String strTemp = responseBody.substring(responseBody.indexOf("<input type=\"hidden\" name=\"execution\" value=\"")+45);
        String execution = strTemp.substring(0,strTemp.indexOf("\""));
        System.out.println("DEBUG:execution:------------------------------------------------->"+execution);
        Header[] headers = getResponseHeader("Set-Cookie");
        for(Header header:headers)
        {
            if(header.getValue().contains("JSESSIONID") == true)
            {
                JSESSIONID = header.getValue().toString();
            }
        }
        return execution;
    }
    public String secondLoginRun(String fullurl, Data indata) throws IOException {
        indata.setHeaderParameters("Content-Type","application/x-www-form-urlencoded");
     //   indata.setHeaderParameters("Content-Type", "application/json;charset=UTF-8");

        String responseBody = requestForXML(fullurl,indata);
        Header[] headers = getResponseHeader("Set-Cookie");
        String CASTGC = null;
        for(Header header:headers)
        {
            if(header.getValue().contains("CASTGC") == true)
            {
                CASTGC = header.getValue().toString();
            }
        }
        return CASTGC;
    }

    public String getwksso(String fullurl, Data indata) throws IOException {
        String responseBody = requestForwksso(fullurl,indata);
        Header[] headers = getResponseHeader("Set-Cookie");
        String wksso = null;
        for(Header header:headers)
        {
            if(header.getValue().contains("wksso") == true)
            {
                wksso = header.getValue().toString();
            }
        }
        this.getData().isRedirect=true;
        return wksso;
    }

    public String getWKSSO(String name,String password,String url) throws Exception {
        this.setHeaderParam("Content-Type", "application/x-www-form-urlencoded");
        this.setHeaderParam("Host", "yun2.test.wkzf");
        this.setParam("username", name, "string");
        this.setParam("password", password, "string");
        this.setParam("lt", "LT-222-5wiLBKvflet2vaGJvwlcNlpCrpoGDb", "string");
        this.setParam("_eventId", "submit", "string");
        this.setParam("submit", "", "string");
        String execution = this.firstLoginRun(ConfigConstantsTest.SSO_BASE_URL + this.URL,this.getData());
        this.setParam("execution", execution, "string");
        this.setHeaderParam("Cookie", this.JSESSIONID);
        System.out.println("DEBUG:JSESSIONID:----------->"+this.JSESSIONID);
        this.getData().isRedirect=false;
        String CASTGC = this.secondLoginRun(ConfigConstantsTest.SSO_BASE_URL + this.URL,this.getData());
        this.setHeaderParam("Cookie", CASTGC);
      //  this.getData().isRedirect=true;
        return this.getwksso(url,this.getData());

    }

    public String getHrWKSSO(String name,String password,String url) throws Exception {
        this.setHeaderParam("Content-Type", "application/x-www-form-urlencoded");
        this.setParam("username", name, "string");
        this.setParam("password", password, "string");
        this.setParam("lt", "LT-222-5wiLBKvflet2vaGJvwlcNlpCrpoGDb", "string");
        this.setParam("_eventId", "submit", "string");
        this.setParam("submit", "", "string");
        String execution = this.firstLoginRun(ConfigConstantsTest.SSO_BASE_URL + this.URL,this.getData());
        this.setParam("execution", execution, "string");
        this.setHeaderParam("Host", "hryun2.test.wkzf:8178");
        this.setHeaderParam("Cookie", this.JSESSIONID);
        System.out.println("DEBUG:JSESSIONID:----------->"+this.JSESSIONID);
        String CASTGC = this.secondLoginRun(ConfigConstantsTest.SSO_BASE_URL + this.URL,this.getData());
        this.setHeaderParam("Cookie", CASTGC);
        return this.getwksso(url,this.getData());
    }

}
