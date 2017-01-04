package com.qa.TestHttpClient;

import java.io.IOException;

import com.qa.Financial2Sys.Financial2Sys;
import com.qa.NewBuildingOpSys.NewBuildingOpSys;
import com.qa.SSOLogin;
import com.qa.constants.ConfigConstantsTest;

public class Test {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SSOLogin legal = new SSOLogin("/login","test","post");
		String wksso = legal.getWKSSO("13162108888","111111","https://yun2.test.wkzf/cpbs/sxzBanner/index.action");

		System.out.println("getWKSSO test>>>>>>>>>>>>>>>."+wksso);
//		try {
//			legal.setHeaderParam("Content-Type", "application/x-www-form-urlencoded");
//			legal.setHeaderParam("Host", "yun2.test.wkzf");
//			legal.setParam("username", "13162108888", "string");
//			legal.setParam("password", "111111", "string");
//			legal.setParam("lt", "LT-222-5wiLBKvflet2vaGJvwlcNlpCrpoGDb", "string");
////			legal.setParam("execution", "e1s1", "string");
//			legal.setParam("_eventId", "submit", "string");
//			legal.setParam("submit", "", "string");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("first test>>>>>>>>>>>>>>>.");
//		String execution = legal.firstLoginRun(ConfigConstantsTest.SSO_BASE_URL + legal.URL,legal.getData());
//		System.out.println("second test>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
//		legal.setParam("execution", execution, "string");
//		legal.setHeaderParam("Cookie", legal.JSESSIONID);
//		System.out.println("JSESSIONID:----------->"+legal.JSESSIONID);
//		String CASTGC = legal.secondLoginRun(ConfigConstantsTest.SSO_BASE_URL + legal.URL,legal.getData());
//		System.out.println("getwksso test>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//		legal.setHeaderParam("Cookie", CASTGC);
//		System.out.println("getwksso test>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>."+legal.getwksso("https://yun2.test.wkzf/fs2/customerReceivables/index",legal.getData()));
//		System.out.println("end test>>>>>>>>>>>>>>>.");
	}

}
