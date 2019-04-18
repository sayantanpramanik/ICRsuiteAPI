package com.TCS.ICRAPI;

public class FormDataResponse 
{
	public String formFields;
	public String formFieldTags;
	public String noOfPages;
	
	public FormDataResponse()
	{
		
	}
	public FormDataResponse(String formFields, String formFieldTags, String noOfPages)
	{
		this.formFields = formFields;
		this.formFieldTags = formFieldTags;
		this.noOfPages = noOfPages;
	}
	
	public String getFormFields()
	{
		return formFields;
	}
	public void setFormFields(String formFields)
	{
		this.formFields = formFields;
	}
	
	public String getFormFieldTags()
	{
		return formFieldTags;
	}
	public void setFormFieldTags(String formFieldTags)
	{
		this.formFieldTags = formFieldTags;
	}
	
	public String getNoOfPages()
	{
		return noOfPages;
	}
	public void setNoOfPages(String noOfPages)
	{
		this.noOfPages = noOfPages;
	}
}
