package main.java.utils;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class newJSONParse {
	
    	
       public  static void Parse(String str){
    	   JSONArray newArray = new JSONArray();  
           JSONObject newJson = new JSONObject();
           JSONObject obj = new JSONObject(str);
           Iterator it = obj.keys();
           while (it.hasNext()) {
        	   String key = (String) it.next();  
               String value = obj.getString(key);  
               JSONArray array = obj.getJSONArray(key); 
               for(int i=0;i<array.length();i++){  
            	   JSONObject jsonobject = array.getJSONObject(i);  
                   jsonobject.put("name", key);  
                   jsonobject.put("exp", key+"="+jsonobject.getString("value"));  
                   newArray.put(jsonobject);
           
               }
           }
           newJson.put("groups",newArray);  
           System.out.println(newJson); 
           
       }  
}
