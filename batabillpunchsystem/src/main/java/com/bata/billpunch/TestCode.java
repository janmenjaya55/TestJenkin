package com.bata.billpunch;

import com.sun.jersey.core.spi.scanning.Scanner;

public class TestCode {
	public static void main(String[] args) {
		// Write a Java Program to reverse a string with using String inbuilt function.
		String str = "Automation";
        StringBuilder str2 = new StringBuilder();
      //  StringBuffer str2 = new StringBuffer();//functionaly same only its synchronised.
        str2.append(str);
        str2 = str2.reverse();     // used string builder to reverse
		System.out.println("Write a Java Program to reverse a string with using String inbuilt function>>>>>>>>>>>"+str2);
		
		// Write a Java Program to reverse a string without using String inbuilt function.
		
		String strone = "Automation";
		String[] sx=strone.split("");
		String cv="";
		for (int i=sx.length-1;i>=0;i--) {
			cv=cv+sx[i];
		}
		System.out.println("Write a Java Program to reverse a string without using String inbuilt function>>>>>"+cv);
		
		
		  //Write a Java Program to find the duplicate characters in a string.
        String str21 = new String("Sakkett");//7
        int count = 0;
        char[] chars = str21.toCharArray();
       
        for (int i=0; i<str21.length();i++) {
                    for(int j=i+1; j<str21.length();j++) {
                               if (chars[i] == chars[j]) {
                                          System.out.println("Duplicate characters are:"+chars[j]);
                                          count++;
                                          break;// for once get the repeate no need to check for the further forward direction
                                }
                     }
         }
		// Write a Java Program to find the second-highest number in an array.
        int arr[] = { 14, 46, 47, 94,100 };
        int largest = 0;
        int secondLargest = 0;
        
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] > largest)
            {
                secondLargest = largest;//only one extra line
                largest = arr[i];
            }
            else if (arr[i] > secondLargest)
            {
                secondLargest = arr[i];
            }
        }
        System.out.println("\nSecond largest number is:" + secondLargest);
        System.out.println("Largest Number is: "  +largest);
		
		//Write a Java Program to check Armstrong number.
		
        int c=0,a,temp;  
        int n=153;//It is the number to check Armstrong  1^3+5^3+3^3
        temp=n;  
        while(n>0)  //it will execute until the n>0 false
        {  
        a=n%10; //to get the  
        n=n/10;  
         c=c+(a*a*a);  
         }  
         if(temp==c)  
         System.out.println("armstrong number"+c);   
         else 
             System.out.println("Not armstrong number"); 
         
         //Write a Java Program to remove all white spaces from a string without using replace().
         
         String str1 = "Saket Saurav        is an Autom ation Engi ne      er";
         
         char[] charsq = str1.toCharArray();
   
         StringBuffer sb = new StringBuffer();
   
         for (int i = 0; i <charsq.length; i++)
         {
             if( (charsq[i] != ' ') )
             {
                 sb.append(charsq[i]);
             }
         } 
         System.out.println("Java Program to remove all white spaces from a string without using replace()"+sb);           //Output : CoreJavajspservletsjdbcstrutshibernatespring
         
         //Write a Java Program for the Fibonacci series. means next 2 value sum will be 3rd num
         
         int num=5, af = 0,bf=0, cf =1;
         for (int i=0; i<num; i++) {
             af = bf;//a=b first=2
             bf = cf;//b=c 2nd=3
             cf = af+bf;//c=a+b 3rd
             System.out.println("Java Program for the Fibonacci series>>>"+af);   
         }   
		
		//java programm for star pattern
         
         int rows = 5;

         for (int i = 1; i <= rows; i++) {
           for (int j = 1; j <= i; j++) {
             System.out.print("* ");
           }
           System.out.println();// exact meaning of empty syso Prints a String and then terminate the line.
          
         }
         //down to left side opposite of upper one
         
         int rowss = 5;

         for (int i = rowss; i >= 1; i--) {
           for (int j = 1; j <= i; j++) {
             System.out.print("* ");
           }
           System.out.println();
         }
         
         //triangle pattern
         
         int rowsa = 5, k = 0;

         for (int i = 1; i < rowsa; ++i, k = 0) {
           for (int space = 1; space <= rowsa - i; ++space) {
             System.out.print("  ");
           }

           while (k != 2 * i - 1) {
             System.out.print("* ");
             ++k;
           }

           System.out.println();
         }
         
         //swap without 3rd var
         
         int x, y;
     
         x = 10;
         y = 23;
     
         System.out.println("Before Swapping\nx = "+x+"\ny = "+y);
     
         x = x + y;
         y = x - y;
         x = x - y;
     
         System.out.println("After Swapping without third variable\nx = "+x+"\ny = "+y);
         
       //swap with 3rd var
    	 x = 10;
         y = 23;
    	 temp = x;
         x = y;
         y = temp;
         System.out.println("After Swapping" + x + y);
         
         
	}
	

}
