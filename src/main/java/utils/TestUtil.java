package main.java.utils;

import java.util.ArrayList;
import org.json.JSONObject;

public class TestUtil {

	public boolean checkParam(JSONObject objResponse, String args) {
		// JSONObject objResponse = this.getObj();
		int i =0;
		int j = 0;
		int test = 0;
		int test2 = 0;
		boolean flag = true;
		ArrayList<String> params = new ArrayList<String>();
		String regex = ",";
		for (int index = 0; index < args.split(regex).length; index++) {
			params.add(args.split(regex)[index]);
		}
		for (String temp : params) {
			if (!objResponse.has(temp)) {
				flag = false;
			}
		}
		return flag;
	}
}
