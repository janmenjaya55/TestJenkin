package com.bata.billpunch;

public class TestingOne extends Thread {
	String tname;
	Thread t;

	private TestingOne(String name) {
		this.tname = name;

		t = new Thread(this, tname);
		System.out.println("New thread: " + t);
		System.out.println("####### tname: " + tname);
		t.start();
		System.out.println("New thread t.getName(): " + t.getName());

	}

	public void run() {
		// put the logic to be executed as per the requirment its acts as a thread work
		System.out.println("child thread logic will be executed here....");
	}

	public static void main(String[] args) {// its by default main thread

		new TestingOne("one");
		new TestingOne("two");
		new TestingOne("three");

		try {
			Thread.sleep(10000);//main thread need to wait 10 sec then its logic will be executed.
		} catch (InterruptedException e) {
			System.out.println("Main thread Interrupted");
		}

		// Thread t=new Thread(new TestingOne());
		// t.start();
		// t.setPriority(MAX_PRIORITY);
		System.out.println("main theread will be execute here.....");

	}

}
