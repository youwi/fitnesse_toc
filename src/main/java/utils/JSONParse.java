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
		// getArrayJsonKey(appendKeys);
	}

	private void parseJson(JSONObject jsonObject, String appendKey) {
		if (null == jsonObject)
			return;
		Iterator<String> it = jsonObject.keys();
		while (it.hasNext()) {
			String key = it.next();
			JSONArray arrayTmp = jsonObject.optJSONArray(key);
			JSONObject objTmp = jsonObject.optJSONObject(key);
			String value = jsonObject.optString(key);

			if (null != arrayTmp) {
				appendKeys.add("/");
				appendKeys.add(key);
				parseJsonArray(arrayTmp);
				System.out.println("analyze-------array");
			} else if (null != objTmp) {
				System.out.println("analyze-------obj");
				appendKeys.add("/");
				appendKeys.add(key);
				parseJson(objTmp, "");
			} else if (value != null && !value.contains("{")) {
				System.out.println("analyze-------value");
				System.out.println("key:------------" + key
						+ "-------value:---------" + value);
				if ("" != getArrayJsonKey(appendKeys)) {
					appendKeys.add("/");
					appendKeys.add(key);
					appendKey = getArrayJsonKey(appendKeys);
				} else {
					appendKey = key;
				}
				getArrayJsonKey(appendKeys);
				jsonMap.put(appendKey, value);
				if ("" != getArrayJsonKey(appendKeys)) {
					appendKeys.remove(appendKeys.size() - 1);
					appendKeys.remove(appendKeys.size() - 1);
				}
			} else {
				System.out.println("arrayTmp value objTmp is all null");
			}

		}
		if ("" != getArrayJsonKey(appendKeys)) {
			appendKeys.remove(appendKeys.size() - 1);
			appendKeys.remove(appendKeys.size() - 1);
		}
	}

	private void parseJsonArray(JSONArray array) {
		String appendKey = "";
		int len = array.length();
		for (int i = 0; i < len; i++) {
			JSONArray arrayTmp = array.optJSONArray(i);
			JSONObject objTmp = array.optJSONObject(i);
			String value = array.optString(i);

			if (null != arrayTmp) {
				parseJsonArray(arrayTmp);
				System.out.println("jsonMap_arrayTmp-------");
			} else if (null != objTmp) {
				System.out.println("jsonMap_objTmp-------");
				parseJson(objTmp, "");
			} else if (value != null && !value.contains("{")) {
				System.out.println("analyze-------value");
				System.out.println("key:------------" + i
						+ "-------value:---------" + value);
				if ("" != getArrayJsonKey(appendKeys)) {
					appendKeys.add("/");
					appendKeys.add(i + "");
					appendKey = getArrayJsonKey(appendKeys);
				}
				// else {
				// appendKey = i+"";
				// }
				getArrayJsonKey(appendKeys);
				jsonMap.put(appendKey, value);
				appendKeys.remove(appendKeys.size() - 1);
				appendKeys.remove(appendKeys.size() - 1);
			} else {
				System.out.println("arrayTmp value objTmp is all null");
			}

		}
		if ("" != getArrayJsonKey(appendKeys)) {
			appendKeys.remove(appendKeys.size() - 1);
			appendKeys.remove(appendKeys.size() - 1);
		}
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

}