package com.revature.controllers;

import java.util.Scanner;

public class DWController {
	private static Scanner scan = new Scanner(System.in);
	
	public static double getAmount() {
		double amount = -1;
		do {
			try {
				System.out.print("$");
				String temp = scan.nextLine().trim();
				amount = Double.parseDouble(temp);
				if(amount< 0) {
					System.out.println("Amount can not be negative.");
				}
			}catch (NumberFormatException e) {
				System.out.println("Only enter in a number");
			}

		}while(amount < 0);
		return amount;
	}
}
