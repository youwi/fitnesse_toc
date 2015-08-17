package main.java.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

public class Data {
    private HashMap<String, Object> map = new HashMap<String, Object>();

    public Data() {
        HashMap<String, String> parameters = new HashMap<String, String>();
    }

    public HashMap<String, Object> getParameters() {
        return this.map;
    }

    public String getAddParam(JSONObject obj) {
    	 	
        Set<Map.Entry<String, Object>> allSet = null;
        allSet = this.getParameters().entrySet();
        Iterator<Map.Entry<String, Object>> iter = allSet.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Object> me = iter.next();
            obj.put(me.getKey().toString(), me.getValue());
            // System.out.println( "\n ()()()("+me.getValue() + ")()" +
            // me.getValue().getClass().getName() +
            // "\n***8743*******************");
        }
        System.out.println(obj);
        return obj.toString();
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

    public void setParameters(String name, Object value) {
        map.put(name, value);
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
    // catch (Exception e) {
    // e.printStackTrace();
    // }

    // this.println(type);
    // }
}
