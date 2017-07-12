package com.qa;

/**
 * IAT
 * Created by yu on 2017/5/20.
 */
public class SetEnv {
    public SetEnv(String env) {
        this.env=env;
    }

    static String env="dev";

    public static String getEnv() {
        return env;
    }

    public static void setEnv(String env) {
        SetEnv.env = env;
    }




}
