package com.TCS.ICRAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.Reader;

import org.json.JSONException;
import org.json.JSONObject;

public class GetFormData 
{
	public static String[] getFields(String formName, String key) throws IOException, JSONException
	{
		String urlName = "http://papersmartaws.azurewebsites.net/webapi/forms/"+formName;
		//HttpURLConnection conn = null;
		InputStream is = new URL(urlName).openStream();
	    try 
	    {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      //return json;
	      String str = json.getString(key);
	      StringTokenizer s = new StringTokenizer(str);
	      ArrayList<String> st = new ArrayList<String>();
	      while(s.hasMoreTokens())
	      {
	          st.add(s.nextToken(","));
	      }
	      String[] fields = st.toArray(new String[0]);
	      return fields;
	    } 
	    finally 
	    {
	      is.close();
	    }
	}
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
}
