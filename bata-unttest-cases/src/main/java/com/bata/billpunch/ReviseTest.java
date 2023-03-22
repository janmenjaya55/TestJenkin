package com.bata.billpunch;

public class ReviseTest {
	public static void main(String[] args) {
		int row = 5,k=0;

		for (int i = 1; i <row; ++i, k = 0) {

			for (int j = 1; j <= row-i; ++j) {
				System.out.print("  ");
			}
			
			while (k != 2 * i - 1) {
				System.out.print("* ");
				++k;
			}

			System.out.println();
		}

	}

}
