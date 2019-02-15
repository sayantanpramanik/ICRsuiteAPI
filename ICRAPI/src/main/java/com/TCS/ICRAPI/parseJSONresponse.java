package com.TCS.ICRAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class parseJSONresponse 
{
	public static StringBuilder main(JSONObject j)throws JSONException
	{
		JSONObject j1 = new JSONObject(j);
		JSONObject j2 = j1.getJSONObject("recognitionResult");
		JSONArray lines = j2.getJSONArray("lines");
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < lines.length(); i++)
		{
			JSONObject im = lines.getJSONObject(i);
			String text = im.getString("text");
			result.append("\n"+text);
		}
		return result;
	}
}
