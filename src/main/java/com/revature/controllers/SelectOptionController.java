package com.revature.controllers;

import java.util.Scanner;

public class SelectOptionController {
	private static Scanner scan = new Scanner(System.in);
	
	public static int selectOption(int num_options) {
		int choice = Integer.parseInt(scan.nextLine().trim());
		while (choice > num_options || choice < 1) {
			System.out.println("Choice not vaild.");
			System.out.println("Please select a vaild choice.");
			String line = scan.nextLine().trim();
			choice = Integer.parseInt(line);
		}
		return choice;
	}
}
