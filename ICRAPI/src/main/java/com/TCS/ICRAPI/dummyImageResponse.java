package com.TCS.ICRAPI;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class dummyImageResponse 
{
	ArrayList<fieldsJson> dummyLines = new ArrayList<fieldsJson>();
	String dummyStatus;
	public dummyImageResponse()
	{
		
	}
	public dummyImageResponse(ArrayList<fieldsJson> dummyLines, String dummyStatus)
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
}
