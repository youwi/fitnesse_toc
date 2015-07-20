package main.java.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParse {
	private HashMap<String, String> jsonMap = new HashMap<String, String>();
	private ArrayList<String> appendKeys = new ArrayList<String>();
	private JSONObject obj = null;

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

	public String getResult(String Key) {
		try {
			System.out.println(jsonMap.get(Key).toString());
			return this.jsonMap.get(Key).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void parseJson(JSONObject jsonObject) {
		this.setObj(jsonObject);
		parseJson(jsonObject, "");
		getArrayJsonKey(appendKeys);
	}

	private void parseJson(JSONObject jsonObject, String appendKey) {
		if (null == jsonObject)
			return;
		try {
			Iterator<String> it = jsonObject.keys();
			while (it.hasNext()) {
				String key = it.next();
				JSONArray array = jsonObject.optJSONArray(key);
				if ("" != getArrayJsonKey(appendKeys)) {
					appendKeys.add("/");
					appendKeys.add(key);
					appendKey = getArrayJsonKey(appendKeys);
				} else {
					appendKey = key;
				}
				if (null == array) {
					String value = jsonObject.optString(key);
					if (value != null && !value.contains("{")) {
						System.out.println("jsonMap_appendKey--------:"
								+ appendKey);
						jsonMap.put(appendKey, value);
					} else {
						appendKeys.add("/");
						appendKeys.add(key);
						parseJson(getJSONObject(value), appendKey);
					}
				} else {
					if ("" != getArrayJsonKey(appendKeys)) {
						appendKeys.add("/");
						appendKeys.add(key);
						appendKey = getArrayJsonKey(appendKeys);
					} else {
						appendKeys.add(key);
						appendKey = key;
					}
					System.out.println("arrayKey:"
							+ getArrayJsonKey(appendKeys));
					parseJsonArray(array);
				}
			}
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

	private int parseJsonArray(JSONArray array) {
		if (null == array)
			return -1;
		int len = array.length();
		for (int i = 0; i < len; i++) {
			JSONObject obj = (JSONObject) array.opt(i);
			@SuppressWarnings("unchecked")
			Iterator<String> it = obj.keys();
			while (it.hasNext()) {
				String key = it.next();
				JSONArray a = obj.optJSONArray(key);
				if (a != null) {
					appendKeys.add("/");
					appendKeys.add(i + "");
					appendKeys.add("/");
					appendKeys.add(key);
					parseJsonArray(a);
				} else {
					appendKeys.add("/");
					appendKeys.add(i + "");
					appendKeys.add("/");
					appendKeys.add(key);
					String jsonKey2 = getArrayJsonKey(appendKeys);
					String value = obj.optString(key);
					if (value != null && !value.contains("{")) {
						System.out.println("jsonMap_jsonKey2--------:"
								+ jsonKey2);
						jsonMap.put(jsonKey2, value);
						appendKeys.remove(appendKeys.size() - 1);
						appendKeys.remove(appendKeys.size() - 1);
						appendKeys.remove(appendKeys.size() - 1);
						appendKeys.remove(appendKeys.size() - 1);
					} else {
						parseJson(getJSONObject(value), jsonKey2);
					}
				}
			}
		}
		appendKeys.remove(appendKeys.size() - 1);
		return 1;
	}

	public String getArrayJsonKey(ArrayList<String> appendKeys) {
		String arrayJsonKey = "";
		for (int i = 0; i < appendKeys.size(); i++) {
			// System.out.println(appendKeys.get(i));
			arrayJsonKey = arrayJsonKey + appendKeys.get(i);
		}
		System.out.println("appendKeys--------:" + arrayJsonKey);
		return arrayJsonKey;
	}

	public HashMap<String, String> getJSONMap() {
		return jsonMap;
	}

}