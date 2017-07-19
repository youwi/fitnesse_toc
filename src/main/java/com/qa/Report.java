package com.qa;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/19.
 */
public class Report {
    /**
     * 用于生成内部报告
     * 因为 调用者来源未知,
     * 需要做统计
     */
    public Report(){
    }
    static{
         Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("请求所有的 URL 个数为:"+ConnectServer._url_count_.size());
            }
        });
    }
}
