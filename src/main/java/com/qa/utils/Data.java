package com.qa.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

public class Data {
    private HashMap<String, Object> map = new HashMap<String, Object>();
    private HashMap<String, String> headermap = new HashMap<String, String>();
    private String jsonParam = null;
    
    public Data() {
        HashMap<String, String> parameters = new HashMap<String, String>();
    }

    public HashMap<String, Object> getParameters() {
        return this.map;
    }
    
    public HashMap<String, String> getHeaderParameters() {
        return this.headermap;
    }
   
    public String getAddParam() {
    	 	
    	JSONObject obj = new JSONObject();
        Set<Map.Entry<String, Object>> allSet = null;
        if(this.getParameters().size() > 0)
        {
        	allSet = this.getParameters().entrySet(); 
            Iterator<Map.Entry<String, Object>> iter = allSet.iterator();
            while (iter.hasNext()) {
                Map.Entry<String, Object> me = iter.next();
                obj.put(me.getKey().toString(), me.getValue());
            }
            return obj.toString();
        }else{
        	return null;
        }
        
    }
    
    
    //get header parameter
    public Iterator<Map.Entry<String, String>> getAddHeaderParam() {
	 	
        Set<Map.Entry<String, String>> allSet = null;
        allSet = this.getHeaderParameters().entrySet(); 
        Iterator<Map.Entry<String, String>> iter = allSet.iterator();
        return iter;
    }
    
    public Map<String, Object> getAddParamMap() {
	 	
        Set<Map.Entry<String, Object>> allSet = null;
        allSet = this.getParameters().entrySet();
        Iterator<Map.Entry<String, Object>> iter = allSet.iterator();
        Map<String, Object> pars = new HashMap<String, Object>();
        while (iter.hasNext()) {
            Map.Entry<String, Object> me = iter.next();
            pars.put(me.getKey().toString(), me.getValue());
            // System.out.println( "\n ()()()("+me.getValue() + ")()" +
            // me.getValue().getClass().getName() +
            // "\n***8743*******************");
        }
        return pars;
    }
    
    //get header parameter
    public Iterator<Map.Entry<String, String>> getAddHeaderParamMap() {
	 	
    	Set<Map.Entry<String, String>> allSet = null;
        allSet = this.getHeaderParameters().entrySet();
        Iterator<Map.Entry<String, String>> iter = allSet.iterator();
        return iter;
        
       
    }
    
    public void setParameters(String name, Object value) {
        map.put(name, value);
    }
    
    
    //set header parameter
    public void setHeaderParameters(String name, String value) {
    	headermap.put(name, value);
    }

    public void println(String type1, String err) {
        if (null == type1) {
            System.out.println("nothing need to do");
        }
        System.out.println(" the " + type1 + " is unknow type");
    }

    public void println(String type1) {
        if (null == type1) {
            System.out.println("nothing need to do");
        }
        System.out.println("The type now is transformed into ----> " + type1
                + '\n');
    }

    public void setParameters(String name, Object value, String type)
            throws Exception {
        if (null == type) {
            this.setParameters(name, value);
        }

        if (null == value || "".equals( value.toString().trim())) {
            value = "";
            type = "string";
        }
        
        switch (type.toLowerCase()) {

        case "fileupload":
            this.setParameters(name,
                    TypeChange.stringToByte64(value.toString()));
            break;
        case "string":
            this.setParameters(name, value.toString());
            break;
        case "number":
            this.setParameters(name, TypeChange.stringToNumber(value.toString()));
            break;
        case "date":
            this.setParameters(name, TypeChange.stringToDate(value.toString()));
            break;
        case "float":
            this.setParameters(name, TypeChange.stringToFloat(value.toString()));
            break;
        case "boolean":
            this.setParameters(name,
                    TypeChange.stringToBoolean(value.toString().toLowerCase()));
            break;
        case "int":
            this.setParameters(name, TypeChange.stringToInt(value.toString()));
        
        case "bigdecimal":
            this.setParameters(name,
                    TypeChange.stringToBigDecimal(value.toString()));
            break;
        case "file":
            this.setParameters(name,
                    TypeChange.stringToByteArray(value.toString()));
            break;
//        case "video":
//            this.setParameters(name,
//                    TypeChange.stringToYouKuByteArray(value.toString()));
//            break;
        default:
            this.println(type, "err");
            Exception e = new Exception();
            e.printStackTrace();
            throw e;

        }
    }

	public String getJsonParam() {
		return jsonParam;
	}

	public void setJsonParam(String jsonParam) {
		if("".equals(jsonParam)!= true)
		{
		this.jsonParam = jsonParam;
		}else{
			this.jsonParam = "{}";
		}
	}
}
