package com.bata.billpunch;

public class TestLevel {
	public static void main(String[] args) {
		int k=0,rows=5;
		
		for(int i=1;i<=rows;++i,k=0) {
			//1,3=2
			for(int sp=1;sp<=rows-i;++sp) {
				System.out.print("  ");//make the loop 2 times to gives 2*2gaps=4
			}
			while(k!=2*i-1) { 
				System.out.print("* ");//for print star like 1,3,5 make a triangle
				++k;
							
			}
			System.out.println();
		}
		
		
		
		
		for(int i=rows;i>=1;i--) {
			
			for(int sp=1;sp<=i;++sp) {
				System.out.print("* ");
			}
			
			System.out.println();
		}
		
		for(int i=1;i<=rows;++i) {
			
			for(int sp=1;sp<=i;++sp) {
				System.out.print("* ");
			}
			
			System.out.println();
		}
		
		 int num=15, a = 0,b=0, c =1;
		for(int i=0;i<num;i++) {
			a=b;
			b=c;
			c=a+b;
			System.out.println(a);
		}
		
		
	}

}
