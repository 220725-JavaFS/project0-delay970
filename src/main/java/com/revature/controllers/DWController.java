package com.revature.controllers;

import java.util.Scanner;

public class DWController {
	private static Scanner scan = new Scanner(System.in);
	
	public static double getAmount() {
		double amount = 0;
		do {
			System.out.print("$");
			String line = scan.nextLine().trim();
			amount = Double.parseDouble(line);
			if(amount< 0) {
				System.out.println("Amount must be positive.");
			}
			
		}while(amount < 0);
		return amount;
	}
}
