package com.revature.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.daos.*;
import com.revature.models.Account;
import com.revature.models.User;

public class WelcomeController {
	private static Scanner scan = new Scanner(System.in);
	private static UserDAO dao = new UserDAOImpl();
	
	public static User welcome() {
		
		System.out.println("Please select an option:");
		System.out.println("1. Register");
		System.out.println("2. Log In");

		//int option = SelectOptionController.selectOption(2);

		int option =2;
		
		switch (option) {
		case 1:
			return createUser();
		case 2:
			return logIn();
		}
		return null;
	}

	private static User logIn() {
		User user;
		int userID;
		while (true) {
			System.out.println("Login");
			System.out.print("Username: ");
			//String username = scan.nextLine().trim();
			
			String username = "delay97";
			
			user = dao.getUserByUsername(username);
			
			System.out.print("Password: ");
			//String password = scan.nextLine().trim();

			String password = "pass";
			
			if (user == null || !user.getPassword().equals(password)) {
				
				System.out.println();
				System.out.println("Username or password is not correct");
				System.out.println("Would you like to create a new account instead?");
				System.out.println("1. Yes");
				System.out.println("2. No");

				int option = SelectOptionController.selectOption(2);

				switch (option) {
				case 1:
					System.out.println();
					return createUser();
				case 2:
					System.out.println();
				}
			} else {
				break;
			}
		}
		return user;
	}

	private static User createUser() {

		String username;

		while (true) {

			System.out.println("Create new user");
			System.out.print("Username: ");

			username = scan.nextLine().trim();

			User user = dao.getUserByUsername(username);

			if (user != null) {
				System.out.println();
				System.out.println("Username already exists.");
				System.out.println("Would you like to login instead?");
				System.out.println("1. Yes");
				System.out.println("2. No");

				int option = SelectOptionController.selectOption(2);

				switch (option) {
				case 1:
					System.out.println();
					return logIn();
				case 2:
					System.out.println();
				}
			} else {
				break;
			}
		}

		String password = createPassword();

		User user = new User(username, password, 1, null);
		user.storeUser();

		int account_id = dao.getUserAccount_id(username);
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		Account checking = new Account(account_id, 1, 0, true);
		checking.storeAccount();
		accounts.add(checking);

		Account savings = new Account(account_id, 2, 0, true);
		savings.storeAccount();
		accounts.add(savings);
		
		user.setAccounts(accounts);

		return user;
	}

	private static String createPassword() {
		while (true) {
			System.out.print("Password: ");
			String pass1 = scan.nextLine().trim();
			System.out.print("Confirm password: ");
			String pass2 = scan.nextLine().trim();
			if (!pass1.equals(pass2)) {
				System.out.println();
				System.out.println("Passwords do not match.");
				System.out.println();
			} else {
				return pass1;
			}
		}

	}
}
