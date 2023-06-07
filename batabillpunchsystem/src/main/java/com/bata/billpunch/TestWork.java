package com.bata.billpunch;

import java.util.Iterator;

public class TestWork {
	public static void main(String[] args) {
		TestWork obj = new TestWork();

		System.out.println("obj>>>>>>>>>>>" + obj.division(10.6f, 50.6f));
		// reverse a string

		String sx = "RERA";
		String snew = "";
		String substring = "";
		char[] ch = sx.toCharArray();
		System.out.println("ch########" + ch[0]);
		System.out.println("ch########ch.length" + (ch.length));
		for (int i = ch.length - 1; i >= 0; i--) {
			System.out.println("ch########ch[i-1]" + ch[i]);
			snew = snew + ch[i];
		}
		System.out.println("reverse string " + snew);

		// reverse a string predefine method
		StringBuilder sb = new StringBuilder(sx);
		sb.reverse();
		System.out.println("predefined" + sb.toString());
		// reverse a string predefine method substring

		substring = substring + sx.substring(sx.length() - 1) + sx.substring(sx.length() - 2, sx.length() - 1)
				+ sx.substring(sx.length() - 3, sx.length() - 2) + sx.substring(sx.length() - 4, sx.length() - 3);
		System.out.println("substring>>>>>>>>>>>>>>" + substring);

	}

	public int division(int a, int b) {//it means in in overloading the return type may change but arg must have to change other wise the method has error during compile time
		System.out.println("intttttttttttttt");
		int result = a / b;
		return result;
	}

	public double division(float a, float b) {
		System.out.println("floatttttttttttt");
		double result = a / b;
		return result;
	}
	/*
	 * public double division(int a, int b) { double result = a / b; return result;
	 * }
	 */

}
