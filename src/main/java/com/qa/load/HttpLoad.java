package com.qa.load;

import com.qa.http.Get;
import com.qa.http.Http;
import com.qa.http.HttpLog;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * fitnesse_toc
 * Created by yu on 2017/8/9.
 * 做一简单的压力测试,目前无集成方案
 * 不考虑与jmeter等超级多线程
 */
public class HttpLoad {

    int countThreadTotal=100;//总线程数
    int countTimeStep=100;//步进时间ms
    int countPreThread=1;// 每线程重进次数
    //不使用时间参数

    /**
     * 无参数GET请求压力测试
     * */
    public void loadGet(String url){
        mta(countThreadTotal, countTimeStep, countPreThread, new Callable() {
            @Override
            public Object call() throws Exception {
                for(int i=0;i<countPreThread;i++){
                    Get get= Http.get(url,1000,60*1000*2);
                    get.text();
                }
                return null;
            }
        });
    }
    public HttpLoad(String url,int countThreadTotal,int countTimeStep ,int countPreThread){
        this.countPreThread=countPreThread;
        this.countThreadTotal=countThreadTotal;
        this.countTimeStep=countTimeStep;
        this.loadGet(url);
    }

    /**
     * 按线程执行任务
     * 线程执行结果还没有保存,TODO
     * @param countThreadTotal 线程数,默认为100
     * @param countTimeStep    线程步进时间,默认为50ms
     * @param countPreThread   线程重试次数,默认为1
     */
    public void mta(int countThreadTotal,int countTimeStep,int countPreThread,Callable task){

        Thread[] threads=new Thread[countThreadTotal];
        long[] expendTime=new long[countThreadTotal];
        final CountDownLatch countDownLatch = new CountDownLatch(countThreadTotal);

        Map stack=new HashMap();
        for(int i=0;i<countThreadTotal;i++){

            int tid=i;//把值复制出来!
            Thread thread=new Thread(()->{
                long stime=System.currentTimeMillis();
                stack.put(tid+"","ing");
                try {
                    task.call();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                long etime=System.currentTimeMillis();
                expendTime[tid]=etime-stime;
                HttpLog.info("Thread Time:" +(etime-stime));
                stack.remove(tid);
                countDownLatch.countDown();
            });
            threads[i]=thread;
        }
        for(int i=0;i<countThreadTotal;i++){
            try {
                Thread.sleep(countTimeStep);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            threads[i].start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }




}
