package com.TCS.ICRAPI;

public class cleanField 
{
	public static String cleanNameField(String s)
	{
		s = cleanText.removeSpaces(s);
		while(s.charAt(0) == ' ')
        {
            s = s.substring(1);
        }
		s = removeUnwantedCharacters(s);
		return s;
	}
	public static String cleanCityField(String s)
	{
		s = cleanText.removeSpaces(s);
		s = removeUnwantedCharacters(s);
		return s;
	}
	public static String cleanStateField(String s)
	{
		s = cleanText.removeSpaces(s);
		s = removeUnwantedCharacters(s);
		return s;
	}
	public static String cleanContactNumberField(String s)
	{
		s = cleanText.removeSpaces(s);
		s = removeUnwantedCharacters2(s);
		/*if(s.length()==10 || s.length()==11)
		{
			return s;
		}
		else
		{
			return "Invalid Contact Number";
		}*/
		return s;
		
	}
	public static String cleanZipCodeField(String s)
	{
		s = cleanText.removeSpaces(s);
		s = removeUnwantedCharacters2(s);
		return s;
	}
	public static String cleanRatingField(String s)
	{
		s = cleanText.removeSpaces(s);
		s = removeUnwantedCharacters2(s);
		return s;
	}
	public static String cleanEmailField(String s)
	{
		try
		{
			while(s.charAt(0) == ' ')
	        {
	            s = s.substring(1);
	        }
			s = cleanText.removeSpaces(s);
			if(s.charAt(0) == '_')
			{
				s = s.substring(1);
			}
			return s;
		}
		catch(Exception e)
		{
			return "";
		}
	}
	public static boolean validateEmail (String s)
	{
		int p = s.lastIndexOf("@");
		if(p == 0)
		{
			return false;
		}
		int l = s.length();
		int p1 = 0;
		for(int i = p+1; i < l; i++)
		{
			if(s.charAt(i) == '.')
			{
				p1 = i;
				break;
			}
		}
		if(p == 0 || p1 == 0)
        {
            return false;
        }
		if((p1-1) == p)
		{
			return false;
		}
		if((p1+1) == l)
		{
			return false;
		}
		return true;
	}
	public static String cleanAge(String s)
	{
		s = cleanText.removeSpaces(s);
		s = removeUnwantedCharacters2(s);
		int n = Integer.parseInt(s);
		if(n >= 0 && n <= 120)
		{
			return s;
		}
		else
		{
			return "Invalid Age";
		}
	}
	public static String cleanDOB(String s)
	{
		short dd = 0, mm = 0, yy = 0;
		int x = 0;
		for(int i = 0; i < s.length(); i++)
		{
			char ch = s.charAt(i);
			if (ch >= '0' && ch <= '9')
			{
				if (x < 2)
				{
					dd = (short) (dd*10+(ch-48));
				}
				else if (x >= 2 && x < 4)
				{
					mm = (short) (mm*10+(ch-48));
				}
				else if (x >= 4)
				{
					yy = (short) (yy*10+(ch-48));
				}
				x++;
			}
		}
		String date = validateDate(dd,mm,yy);
		return date;
	}
	public static String cleanAddress(String s)
	{
		while(s.charAt(0) == ' ')
        {
            s = s.substring(1);
        }
		//s = cleanText.removeSpaces(s);
		String str = "";
		for (int i = 0; i < s.length(); i++)
		{
			char ch = s.charAt(i);
			if (ch == '_')
			{
				str = str+" ";
			}
			else if (ch == ':' || ch == '`' || ch == ' ')
			{
				continue;
			}
			else
			{
				if(ch >= 'a' && ch <= 'z')
                {
                    ch-=32;
                }
				str = str+ch;
			}
		}
		str.trim();
		return str;
	}
	public static String cleanSuggestionsField(String s)
	{
		s = s+" ";
		if(s.charAt(0) == ' ')
		{
			s = s.substring(1);
		}
		String str = "";
		String word = "";
		for(int i = 0; i < s.length(); i++)
		{
			char ch = s.charAt(i);
			if (ch == ' ')
			{
				if(word.charAt(0) == '_')
				{
					if(str.length() == 0)
					{
					    str = str+word.substring(1);
					}
					else
					{
					    str = str+" "+word.substring(1);
					}
				}
				word = "";
			}
			else
			{
				if(ch >= 'a' && ch <= 'z')
                {
                    ch-=32;
                }
				word = word+ch;
			}
		}
		return str;
	}
	public static String removeUnwantedCharacters(String s)
	{
		String str = "";
		for(int i = 0; i < s.length(); i++)
		{
			char ch = s.charAt(i);
			if(ch >= 'A' && ch <= 'Z')
			{
				str = str+ch;
			}
			if(ch >= 'a' && ch <= 'z')
			{
				ch -= 32;
				str = str+ch;
			}
			if(ch == ' ')
			{
				str = str+ch;
			}
		}
		return str;
	}
	public static String removeUnwantedCharacters1(String s)
	{
		String str = "";
		for(int i = 0; i < s.length(); i++)
		{
			char ch = s.charAt(i);
			if(ch >= 'A' && ch <= 'Z')
			{
				str = str+ch;
			}
			if(ch >= 'a' && ch <= 'z')
			{
				ch -= 32;
				str = str+ch;
			}
			if(ch >= '0' && ch <= '9')
			{
				str = str+ch;
			}
		}
		return str;
	}
	public static String removeUnwantedCharacters2(String s)
	{
		String str = "";
		for(int i = 0; i < s.length(); i++)
		{
			char ch = s.charAt(i);
			if(ch >= '0' && ch <= '9')
			{
				str = str+ch;
			}
		}
		return str;
	}
	public static String validateDate(short dd, short mm, short yy)
	{
		short[] days = {0,31,28,31,30,31,30,31,31,30,31,30,31};
		String date = "";
		if (yy < 0)
		{
			//date = "Invalid Date";
		}
		if (mm < 0 || mm > 12)
		{
			//date = "Invalid Date";
		}
		if((yy % 100) == 0)
		{
			if ((yy % 400)==0)
			{
				days[3] = 29;
			}
		}
		else if ((yy % 4) == 0)
		{
			days[3] = 29;
		}
		if (dd < 10)
		{
			date = date+"0"+dd+"/";
		}
		else
		{
			date = date+dd+"/";
		}
		
		if (mm < 10)
		{
			date = date+"0"+mm+"/";
		}
		else if (mm > 10)
		{
			date = date+mm+"/";
		}
		date = date+yy;
		return date;
	}
}
