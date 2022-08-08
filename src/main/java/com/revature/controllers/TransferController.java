package com.revature.controllers;

import java.util.Scanner;

public class TransferController {
	private static Scanner scan = new Scanner(System.in);

	public static int inputNum() {
		while (true) {
			try {
				String temp = scan.nextLine().trim();
				int num = Integer.parseInt(temp);
				return num;
			} catch (NumberFormatException e) {
				System.out.println("Only enter in a number");
			}

		}
	}
}
