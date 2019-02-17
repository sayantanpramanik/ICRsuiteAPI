package com.tcs.tcsicr.view;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class imagesJson 
{
	public ArrayList<linesJson> images = new ArrayList<linesJson>();
	//String status;
	
	public imagesJson()
	{
		
	}
	public imagesJson(ArrayList<linesJson> images)
	{
		this.images=images;
		//this.status=status;
	}
	public ArrayList<linesJson> getImages()
	{
		return images;
	}
	public void setImages(ArrayList<linesJson> images)
	{
		this.images=images;
	}
	//public String getStatus()
	//{
		//return status;
	//}
	//public void setStatus(String status)
	//{
		//this.status=status;
	//}
}
