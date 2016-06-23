package main.java.TestHttpClient;

import java.util.Iterator;
import java.util.Map.Entry;

public interface HttpRequestCallback {
	String addJsonParam();
    String addParam();
    Iterator<Entry<String, String>> AddHeaderParameters();
    
}
