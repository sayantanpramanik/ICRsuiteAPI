package com.tcs.tcsicr.view;

public class fieldsJson 
{
	public String key;
	public String value;
	public String confidence;
	
	public fieldsJson()
	{
		
	}
	public fieldsJson(String key, String value, String confidence)
	{
		this.key = key;
		this.value = value;
		this.confidence = confidence;
	}
	public String getKey()
	{
		return key;
	}
	public void setKey(String key)
	{
		this.key = key;
	}
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	public String getConfidence()
	{
		return confidence;
	}
	public void setConfidence(String confidence)
	{
		this.confidence = confidence;
	}
}
