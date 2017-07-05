package com.qa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IAT
 * Created by yu on 2017/5/20.
 */
public class SetGlobalHeader {

    public static Map<String,String> headersMap=new HashMap();//只允许一个 header存在

    public void haoLieLogin(String mobile,String password,String channel){


    }

    public SetGlobalHeader(String headerName,String headerValue) {
         headersMap.put(headerName,headerValue);
     }
}
