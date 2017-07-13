package com.qa.fit;


import fitlibrary.DoFixture;

import java.util.Arrays;

/**
 * Created by yu on 16/8/25.
 */
public class MyFit extends DoFixture {

    public String letters;
    public void fillTimesWith(int count,char c){
        char[] arr=new char[count];
        Arrays.fill(arr, c);
        letters=new String(arr);
    }

    public void setList(char[] array){
        letters=new String(array);
    }
    public char charAt(int position){
        return letters.charAt(position);
    }
}
