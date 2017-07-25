package com.qa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.qa.utils.GsonJsonUtil.gson;

/**
 * fitnesse_toc
 * Created by yu on 2017/7/25.
 */
public class DataMaker {

    /**
     * 生成一个随机数
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString();
    }

    /**
     * 生成一个时间线 ID 毫秒为结果
     * 如: 2017-01-01 12:12:00=>2017.01.01.12.12.00.000
     * 带点
     * @return
     */
    public  static String tid(){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.sss");
        return   sf.format(new Date() );
    }
    public static String tid(String mat){
        SimpleDateFormat sf=new SimpleDateFormat(mat);
        return   sf.format(new Date() );
    }

    /**
     * 生成数字数组,默认为100个
     * @return
     */
    public static String intArray(){
        return intArray(100);
    }
    public static String intArray(int length){
        List list=new ArrayList();
        for(int i=0;i<length;i++){
            list.add(Math.floor(Math.random()*1000));
        }
        return  gson.toJson(list);
    }

    /**
     * 例如:
     * [{a:1,b:1}]=>[ {a:0,b:0},{a:1,b:1}....}]
     * 随机数组生成,需要源数
     * @return
     */
    public static String  pend(String src){
        //TODO
        return src;
    }

    public static String  pendArray(String src){
        //TODO
        return src;
    }




}
