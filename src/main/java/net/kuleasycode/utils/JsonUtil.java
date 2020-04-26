package net.kuleasycode.utils;

import com.google.gson.Gson;

public class JsonUtil {
	
	public static String convertObjectToJson(Object object) {
		Gson gson = new Gson();
		String json = gson.toJson(object);
		return json;
	}

}
