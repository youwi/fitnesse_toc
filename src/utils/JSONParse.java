package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParse {
    private HashMap<String, String> jsonMap = new HashMap<String, String>();
    private HashMap<String, String> h_jsonMap = new HashMap<String, String>();
    private ArrayList<String> jsonKey = new ArrayList<String>();
    private JSONObject obj = null;
//    private net.sf.json.JSONObject obj2 = null;
    private boolean flg;
    private int layer;

    public boolean checkParam(String args) {
        JSONObject objResponse = this.getObj();
        boolean flag = true;
        ArrayList<String> params = new ArrayList<String>();
        String regex = ",";
        if (0 == args.split(regex).length) {
            params.add(args);
        } else {
            for (int index = 0; index < args.split(regex).length; index++) {
                params.add(args.split(regex)[index].trim());
            }
            for (String temp : params) {
                if (!objResponse.has(temp)) {
                    flag = false;
                }
            }
            return flag;
        }
        return false;
    }

    public JSONObject getObj() {
        return this.obj;
    }

    public void setObj(JSONObject obj) {
        this.obj = obj;
    }
    
//    public void setObj2(net.sf.json.JSONObject obj) {
//        this.obj2 = obj;
//    }

    public String getResult(String Key) {
        try {
            System.out.println();
            System.out.println(h_jsonMap);
            System.out.println();
            System.out.println(jsonMap.get(Key).toString());
            return this.jsonMap.get(Key).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void parseJson(JSONObject jsonObject) {
        this.setObj(jsonObject);
        parseJson(jsonObject, null);
    }
    
//    public void parseJson2(net.sf.json.JSONObject jsonObject) {
//        this.setObj2(jsonObject);
//        parseJson2(jsonObject,null);
//    }

    private void parseJson(JSONObject jsonObject, String appendKey) {
        if (null == jsonObject)
            return;
        try {
            Iterator<String> it = jsonObject.keys();
            while (it.hasNext()) {
                String key = it.next();
                JSONArray array = jsonObject.optJSONArray(key);
                appendKey = key;
                if (null == array) {
                    String value = jsonObject.optString(key);
                    if (value != null && !value.contains("{")) {
                        if (false == flg) {
                            h_jsonMap.put(appendKey, value.toString());
                        }
                        jsonMap.put(appendKey, value);
                        jsonKey.add(appendKey);
                    } else {
                        parseJson(getJSONObject(value), appendKey);
                    }
                } else {
                    parseJsonArray(array, -1, layer);
                }
            }
            flg = true;
        } catch (Exception e) {
            e.toString();
        }
    }

    public JSONObject getJSONObject(String json) {
        try {
            JSONObject result = new JSONObject(json);
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int parseJsonArray(JSONArray array, int index, int layer) {
        if (null == array)
            return layer;
        int lastLayer = layer++;
        int len = array.length();
        for (int i = 0; i < len; i++) {
            JSONObject obj = (JSONObject) array.opt(i);
            @SuppressWarnings("unchecked")
            Iterator<String> it = obj.keys();
            while (it.hasNext()) {
                String key = it.next();
                JSONArray a = obj.optJSONArray(key);
                if (a != null)
                    parseJsonArray(a, i, layer);
                else {
                    JSONObject jsonObject = getJSONObject(obj.optString(key));
                    String jsonKey = i + "/" + lastLayer + "/" + key;
                    parseJson(jsonObject, jsonKey);
                }
            }
        }
        return layer;
    }

    public ArrayList<String> getJsonKey() {
        return this.jsonKey;
    }

    public HashMap<String, String> getJSONMap() {
        return jsonMap;
    }

    public HashMap<String, String> getH_jsonMap() {
        return h_jsonMap;
    }

    public void setH_jsonMap(HashMap<String, String> h_jsonMap) {
        this.h_jsonMap = h_jsonMap;
    }
}