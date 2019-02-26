package com.tcs.tcsicr.view;

public class searchSubstring { 
    
    // Returns true if s1 is substring of s2 
    public static int isSubstring(String s1, String s2) 
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
                return (i+M); 
        } 
      
        return -1; 
    } 
} 
