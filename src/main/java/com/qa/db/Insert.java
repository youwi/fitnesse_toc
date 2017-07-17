package com.qa.db;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qa.utils.GsonJsonUtil;

import java.util.Map;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/17.
 */
public class Insert extends SQL {



    public Insert(String sql){
        super(sql);
    }
    public Insert(String tableName,String kv){

    }

    public static Map  kvToMap(String kv){

        Gson gson= GsonJsonUtil.gsonLower;
       return gson.fromJson(kv,Map.class);

    }


}
