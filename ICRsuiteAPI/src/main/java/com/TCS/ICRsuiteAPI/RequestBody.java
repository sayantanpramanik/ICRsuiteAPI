package com.TCS.ICRsuiteAPI;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RequestBody 
{
	String imageFormat;
	String[] images;
    
    public RequestBody()
    {
    	
    }   
    public RequestBody(String imageFormat, String[] images)
    {
    	this.imageFormat=imageFormat;
    }   
    public String getImageFormat()
    {
    	return imageFormat;
    }
    public String[] getImages() 
    {
		return images;
	}
    public void setImageFormat(String imageFormat)
    {
    	this.imageFormat=imageFormat;
    }
    public void setImages(String[] images) 
    {
		this.images = images;
	}
}
