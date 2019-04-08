package com.TCS.ICRAPI;

import java.util.ArrayList;

public class lineJson 
{
	ArrayList<wordJson> words = new ArrayList<wordJson>();
	String text;
	
	public lineJson()
	{
		
	}
	public lineJson(ArrayList<wordJson> words, String text)
	{
		this.words=words;
		this.text=text;
	}
	public ArrayList<wordJson> getWords()
	{
		return words;
	}
	public void setWords(ArrayList<wordJson> words)
	{
		this.words=words;
	}
	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text=text;
	}
}
//git test