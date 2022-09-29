package com.qa.util;

import org.json.JSONObject;

public class TestUtil {

	public static String jsonParse(JSONObject json, String name) {
		//System.out.println("Value of " + name + " is  " + json.get(name).toString());
		//System.out.println(json.get("data").toString());
		
		return json.get(name).toString();
	}
}
