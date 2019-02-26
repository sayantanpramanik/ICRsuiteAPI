package com.tcs.tcsicr.view;

public class cleanText 
{
	public static String clean(String s)
	{
		int l = s.length();
		String s1 = "";
		for(int i = 0; i < l; i++)
		{
			if (s.charAt(i)=='.'||s.charAt(i)==':'|| s.charAt(i)=='_')
			{
				continue;
			}
			s1+=s.charAt(i);
		}
		if(s1.charAt(0)==' ')
		{
			s1 = s1.substring(1);
		}
		s1 = s1.trim();
		return s1;
	}
}
