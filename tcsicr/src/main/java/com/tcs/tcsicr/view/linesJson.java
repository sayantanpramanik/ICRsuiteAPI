package com.tcs.tcsicr.view;

import java.util.ArrayList;

import com.tcs.tcsicr.view.lineJson;

public class linesJson 
{
	public ArrayList<lineJson> lines = new ArrayList<lineJson>();
	public String status;
	
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
