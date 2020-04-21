package com.ferns.rough;

import java.util.Date;

public class TestTimestamp {

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date.toString().replace(":", " ").replace(" ", "_")+".png");
	}
	
}
