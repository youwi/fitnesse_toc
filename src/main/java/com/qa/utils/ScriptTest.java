package com.qa.utils;

/**
 * Created by yu on 16/8/23.
 */
public class ScriptTest {

    String a="";

    public String getB() {
        return b;
    }

    public boolean setB(String b) {
        this.b = b;
        return true;
    }

    public String getA() {
        return a;
    }

    public boolean setA(String a) {
        this.a = a;
        return true;
    }

    String b="";

//    public   ScriptTest(){
//        a="nnn";
//        b="nnnn";
//
//    }
    public   ScriptTest(String in){
        System.out.println(in);

    }
    public String showAb(){
        return a+b;
    }

    public String sk(){

        return "<h1>ERROR</h1>";
    }


}
