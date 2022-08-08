package com.revature.controllers;

import java.util.Scanner;

public class SelectOptionController {
	private static Scanner scan = new Scanner(System.in);

	public static int selectOption(int num_options) {

		int choice = 0;
		do {
			try {
				String temp = scan.nextLine().trim();
				choice = Integer.parseInt(temp);
				System.out.println();
				if (choice > num_options || choice < 1) {
					System.out.println("Choice not vaild.");
					System.out.println("Please select a vaild choice.");
					
				}
			} catch (NumberFormatException e) {
				System.out.println("Only enter in a number");
			}

		} while (choice > num_options || choice < 1);
		return choice;
	}
}
