package com.revature.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.Account;
import com.revature.models.User;

public class NewUserAdvancedController {

	private static Scanner scan = new Scanner(System.in);
	private static UserDAO dao = new UserDAOImpl();

	public static void createUser() {

		String username;

		while (true) {

			System.out.println("Create new user");
			System.out.print("Username: ");

			username = scan.nextLine().trim();

			User user = dao.getUserByUsername(username);

			if (user != null) {
				System.out.println();
				System.out.println("Username already exists.");

			} else {
				break;
			}
		}

		String password = createPassword();

		System.out.println("Select user permission level.");

		ArrayList<String> permissions = dao.getPermissionTypes();

		for (int i = 0; i < permissions.size(); i++) {
			System.out.println(i + 1 + ". " + permissions.get(i));
		}

		int choice = SelectOptionController.selectOption(permissions.size());

		User user = new User(username, password, choice, null);
		user.storeUser();

		if (choice == 1) {
			int account_id = dao.getUserAccount_id(username);
			ArrayList<Account> accounts = new ArrayList<Account>();

			Account checking = new Account(account_id, 1, 0, true);
			checking.storeAccount();
			accounts.add(checking);

			Account savings = new Account(account_id, 2, 0, true);
			savings.storeAccount();
			accounts.add(savings);

			user.setAccounts(accounts);
		}
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
