package com.bata.billpunch;

public final class Immutable {//class need to be final

	private final String pancardNumber;// private final

	public Immutable(String pancardNumber) {
		this.pancardNumber = pancardNumber;
	}

	public String getPancardNumber() {//only get to be present
		return pancardNumber;
	}

	public static void main(String ar[]) {
		Immutable e = new Immutable("ABC123");
		String s1 = e.getPancardNumber();
		System.out.println("Pancard Number: " + s1);
	}
}
