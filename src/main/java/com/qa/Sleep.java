package com.qa;

/**
 * fitnesse_toc
 * Created by yu on 2017/7/24.
 * 由于不能直接调用静态方法,需要来包装一个方法
 */
public class Sleep {
    public Sleep() {
    }
    public Sleep(int timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
