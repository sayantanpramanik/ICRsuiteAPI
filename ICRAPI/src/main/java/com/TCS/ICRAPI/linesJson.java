package com.TCS.ICRAPI;

import java.util.ArrayList;

public class linesJson 
{
	ArrayList<lineJson> lines = new ArrayList<lineJson>();
	String status;
	
	public linesJson()
	{
		
	}
	public linesJson(ArrayList<lineJson> lines, String status)
	{
		this.lines=lines;
		this.status=status;
	}
	public ArrayList<lineJson> getLines()
	{
		return lines;
	}
	public void setLines(ArrayList<lineJson> lines)
	{
		this.lines=lines;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status=status;
	}
}
