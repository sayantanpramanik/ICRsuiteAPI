package com.TCS.ICRAPI;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class dummyImageResponse 
{
	ArrayList<fieldsJson> dummyLines = new ArrayList<fieldsJson>();
	String dummyStatus;
	String message;
	public dummyImageResponse()
	{
		
	}
	public dummyImageResponse(ArrayList<fieldsJson> dummyLines, String dummyStatus, String message)
	{
		this.dummyLines = dummyLines;
		this.dummyStatus = dummyStatus;
		this.message = message;
	}
	public ArrayList<fieldsJson> getDummyLines()
	{
		return dummyLines;
	}
	public void setDummyLines(ArrayList<fieldsJson> dummyLines)
	{
		this.dummyLines = dummyLines;
	}
	public String getDummyStatus()
	{
		return dummyStatus;
	}
	public void setDummyStatus(String dummyStatus)
	{
		this.dummyStatus = dummyStatus;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
}
