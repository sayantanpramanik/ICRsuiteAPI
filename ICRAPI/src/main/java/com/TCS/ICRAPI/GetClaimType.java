package com.TCS.ICRAPI;

public class GetClaimType 
{
	public static String main(String s)
	{
		String claim = "";
		int[] x = new int[3];
		String[] claimOptions = {"INDIVIDUAL", "FAMILY", "CORPORATE"};
		String str = "";
		int n = 0;
		for (int i = 0; i < claimOptions.length; i++)
		{
			x[i] = isSubstring1(claimOptions[i], s);
			if(i == 0)
			{
				str = s.substring(0,x[i]);
			}
			else
			{
				str = s.substring((x[i-1]),x[i]);
			}
			//System.out.println(x[i]+str);
			boolean flag = true;
			for(int j = 0; j < str.length(); j++)
			{
				char ch = str.charAt(j);
				if(ch == 'O' || ch == 'Q' || ch == 'o' || ch == '0' || ch == '@')
				{
					flag = false;
				}
			}
			if(flag == true)
			{
				claim = claimOptions[i];
				n++;
			}
		}
		if(n == 1)
		{
			return claim;
		}
		else 
		{
			return "Claim Type Not Detected Properly";
		}
	}
	static int isSubstring1(String s1, String s2) 
    { 
        int M = s1.length(); 
        int N = s2.length(); 
      
        /* A loop to slide pat[] one by one */
        for (int i = 0; i <= N - M; i++) { 
            int j; 
      
            /* For current index i, check for 
            pattern match */
            for (j = 0; j < M; j++) 
                if (s2.charAt(i + j) != s1.charAt(j)) 
                    break; 
      
            if (j == M) 
                return (i); 
        } 
      
        return -1; 
    } 
}
