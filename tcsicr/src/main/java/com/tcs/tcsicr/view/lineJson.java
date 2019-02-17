package com.tcs.tcsicr.view;

import java.util.ArrayList;

import com.tcs.tcsicr.view.wordJson;

public class lineJson 
{
	public ArrayList<wordJson> words = new ArrayList<wordJson>();
	public String text;
	
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
