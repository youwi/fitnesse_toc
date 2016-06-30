package com.qa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lifang.json.FasterJsonTool;
import com.lifang.model.Response;
import com.lifang.userManager.model.HttpRequestUserLoginModel;
import com.lifang.userManager.rmi.RMIUserLoginService;

@Controller
@RequestMapping("/index")
public class IndexController 
{
	@Autowired
	private RMIUserLoginService rmiIUserLoginServiceImpl;
	
	@RequestMapping("index.shtml")
	public void index(HttpRequestUserLoginModel param) throws Exception
	{
//		HttpRequestUserLoginModel param = new HttpRequestUserLoginModel();
//		param.setMobilePhoneNum("13564346449");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>start index.html");
		Response<String> response = rmiIUserLoginServiceImpl.validateMobilePhoneAvaliable(param);
        System.out.println(">>>>>>>>>>>get response:" + FasterJsonTool.writeValueAsString(response));
	}
}
