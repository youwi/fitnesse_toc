package com.qa;

import com.qa.utils.GsonJsonUtil;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * fitnesse_toc
 * Created by yu on 2018/3/5.
 */
public class SpSync {


    /**
     * 为同步服务单独做的一个测试方法.
     */
    public static void sync_umock() {
        ConnectServer.urlMapMerge(ConnectServer._URL_COUNT_);
        ConnectServer._URL_COUNT_ = ConnectServer.sortMap(ConnectServer._URL_COUNT_);
        ConnectServer cs;

        String p_cw = "http://172.16.52.181:8101/39";
        String p_c = "http://172.16.52.181:8101/66";
        String p_hr = "http://172.16.52.181:8101/67";

        cs = new ConnectServer(p_cw);
        cs.get();
        Map uc_cw = GsonJsonUtil.gson.fromJson(cs.responseBody, Map.class);

        cs = new ConnectServer(p_c);
        cs.get();
        Map uc_c = GsonJsonUtil.gson.fromJson(cs.responseBody, Map.class);

        cs = new ConnectServer(p_hr);
        cs.get();
        Map uc_hr = GsonJsonUtil.gson.fromJson(cs.responseBody, Map.class);

        Map uc_cw_new = new HashMap();
        Map uc_c_new = new HashMap();
        Map uc_hr_new = new HashMap();

        String key_c = "http://www.lieluobo.testing/api/biz";
        String key_crm = "http://crm.lieluobo.testing/api/biz";
        String key_cw = "http://crm.lieluobo.testing/api/biz";
        String key_hr = "http://crm.lieluobo.testing/api/biz";
        String key_http = "http://";

        for (String key : ConnectServer._URL_COUNT_.keySet()) {
            key = key.replace("POST:", "post:");
            key = key.replace("GET:", "get:");

            if (key.contains(key_c))
                uc_c_new.put(key.replace(key_c, ""), 2);
            if (key.contains(key_crm))
                uc_cw_new.put(key.replace(key_crm, ""), 2);
            if (key.contains(key_cw))
                uc_cw_new.put(key.replace(key_cw, ""), 2);
            if (key.contains(key_hr))
                uc_hr_new.put(key.replace(key_hr, ""), 2);
            if (key.contains(key_http))
                uc_c_new.put(key, 2);

        }


        uc_cw_new.putAll(uc_cw);
        uc_c_new.putAll(uc_c);
        uc_hr_new.putAll(uc_hr);

        cs = new ConnectServer(p_cw);
        cs.setBody(GsonJsonUtil.gson.toJson(uc_cw_new));
        cs.post();

        cs = new ConnectServer(p_c);
        cs.setBody(GsonJsonUtil.gson.toJson(uc_c_new));
        cs.post();

        cs = new ConnectServer(p_hr);
        cs.setBody(GsonJsonUtil.gson.toJson(uc_hr_new));
        cs.post();

        return;

    }
}

