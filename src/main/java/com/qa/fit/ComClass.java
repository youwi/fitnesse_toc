package com.qa.fit;

/**
 * Created by yu on 16/8/25.
 */
public class ComClass {

    public boolean aa(){
        try {
            Thread.currentThread().sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean bb(){
        try {
            Thread.currentThread().sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean cc(){
        return true;
    }

    public boolean dd(){
        return true;
    }

    public boolean ee(){
        return true;
    }
}
