package com.tcs.tcsicr.view;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class dummyImageResponse 
{
	public ArrayList<fieldsJson> dummyLines = new ArrayList<fieldsJson>();
	public String dummyStatus;
	public String message;
	public dummyImageResponse()
	{
		
	}
	public dummyImageResponse(ArrayList<fieldsJson> dummyLines, String dummyStatus, String message)
	{
		this.dummyLines = dummyLines;
		this.dummyStatus = dummyStatus;
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
