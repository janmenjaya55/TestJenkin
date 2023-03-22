package com.bata.billpunch;

public abstract class TestAbstract {

	int x; // for this default const req.not static mean instance
	static int  y;//statics bcz of const 
	final int z=9;//final
	
	TestAbstract(int y){
		this.y=y;
	}

		public abstract String m1();
		public abstract String m2();
		
		public  void   m3() {//default
			System.out.println("jjjjjjjjjjjjjjjjjjjjjjjj>>>"+x);
		}
		
		public static void   m4() {//static
			System.out.println("hjgjgjhdgfhhjb>>>");
		}
		

}
