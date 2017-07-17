package com.qa.TestHttpClient;

import com.qa.utils.HttpClientUtil;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/13.
 */
public class HttpClientUtilTest {

    @org.testng.annotations.Test
    private void urlParamMatcherTest(){

        assert "/abc/1234/id?cc=bb" .equals(HttpClientUtil.urlParamMatcher("/abc/1234/id","cc=bb"));

        assert "/abc/1234/id?cc=bb" .equals(HttpClientUtil.urlParamMatcher("/abc/{abc}/id","abc=1234&cc=bb"));
        assert "/abc/abc/id" .equals(HttpClientUtil.urlParamMatcher("/abc/abc/id",""));


    }
}