package com.bata.billpunch;

public class singleWiththread extends Thread {
	//singleton with thread concept
	private static singleWiththread var;
	
	private singleWiththread() {
		
		System.out.println("construtor will executed here.");
	}
	
	public void run() {
		System.out.println("thread t.run method will executed here.");
		singleWiththread.getsingleWiththread();//call for getintance for instantiate new obj.
	}
	
	public static singleWiththread getsingleWiththread() {
		
		
		 synchronized (singleWiththread.class) {//lock for a current thread once it release then next thread work will be happen
			 if (var==null) {
					var=new singleWiththread();
					System.out.println("object will be created as per the var is null###########"+var.hashCode());
				}
		}
		//793102106 hashcode
		return var;
	}
	public static void main(String[] args) {
		
		Thread t=new Thread(new singleWiththread());//pass the new object which will initialisedin const.
		t.start();
		System.out.println("thread t.start will executed here.");
		System.out.println("############singleWiththread main>>>>>>>");
	}

}
