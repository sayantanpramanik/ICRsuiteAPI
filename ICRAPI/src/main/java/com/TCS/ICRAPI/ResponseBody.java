package com.TCS.ICRAPI;


import org.json.JSONObject;


public class ResponseBody 
{
	JSONObject icrResults;
	
	public ResponseBody()
	{
	
	}
	public ResponseBody(JSONObject icrResults)
	{
		this.icrResults = icrResults;
	}
	public JSONObject getIcrResults()
	{
		return icrResults;
	}
	public void setIcrResults(JSONObject icrResults)
	{
		this.icrResults = icrResults;
	}
}
