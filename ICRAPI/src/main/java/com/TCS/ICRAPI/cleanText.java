package com.TCS.ICRAPI;

public class cleanText 
{
	public static String removeSpaces(String s)
	{
		int l = s.length();
		String s1 = "";
		for(int i = 0; i < l; i++)
		{
			if (s.charAt(i)==' ')
			{
				continue;
			}
			s1+=s.charAt(i);
		}
		s1 = s1.trim();
		return s1;
	}
	public static String removeUnderScore(String s)
	{
		String str = "";
		for(int i=0; i<s.length(); i++)
		{
			char ch = s.charAt(i);
			if (ch=='_')
			{
				if(containsMoreText(s,i)==true)
				{
					str=str+" ";
				}
				else
				{
					continue;
				}
			}
			else
			{
				str=str+ch+"";
			}
		}
		return str;
	}
	public static boolean containsMoreText(String s, int x)
	{
		for(int i = x; i < s.length(); i++)
		{
			char ch = s.charAt(i);
			if(ch >= 'A' && ch <= 'Z')
			{
				return true;
			}
			if(ch >= 'a' && ch <= 'z')
			{
				return true;
			}
			if(ch >= '0' && ch <= '9')
			{
				return true;
			}
		}
		return false;
	}
}
