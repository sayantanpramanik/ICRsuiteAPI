package com.TCS.ICRAPI;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class dummyRequest 
{
	String imageFormat;
	String formType;
	String[] images;
    
    public dummyRequest()
    {
    	
    }   
    public dummyRequest(String imageFormat, String formType, String[] images)
    {
    	this.formType = formType;
    	this.imageFormat = imageFormat;
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
    	this.imageFormat = imageFormat;
    }
    public void setImages(String[] images) 
    {
		this.images = images;
	}
    public String getFormType()
    {
    	return formType;
    }
    public void setFormType(String formType)
    {
    	this.formType = formType;
    }
}
