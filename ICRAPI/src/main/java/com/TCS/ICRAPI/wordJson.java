package com.TCS.ICRAPI;

public class wordJson 
{
	String text;
	String confidence;
	
	public wordJson()
	{
		
	}
	public wordJson(String text)
	{
		this.text=text;
		//this.confidence=confidence;
	}
	public void setText(String text)
	{
		this.text=text;
	}
	public void setConfidence(String confidence)
	{
		this.confidence=confidence;
	}
	public String getText()
	{
		return text;
	}
	public String getConfidence()
	{
		return confidence;
	}
}
