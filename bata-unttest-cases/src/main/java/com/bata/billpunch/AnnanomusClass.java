package com.bata.billpunch;

public class AnnanomusClass {
	
	
	public AnnanomusClass(){
		System.out.println("default constructor");
	}
	
		public AnnanomusClass(int i){
		System.out.println("parameterized constructor");
	}
	
	{
		System.out.println("Object is created by the ");
	}
	
	public static void main(String arr[]){
		AnnanomusClass b1 = new AnnanomusClass();
		AnnanomusClass b2 = new AnnanomusClass(1);
	}
	

}
