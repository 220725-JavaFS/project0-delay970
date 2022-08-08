package com.revature.services;

import com.revature.models.User;
import com.revature.controllers.DWController;
import com.revature.controllers.SelectOptionController;
import com.revature.controllers.TransferController;
import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOImpl;

import java.util.ArrayList;

import com.revature.models.Account;
import com.revature.models.Transaction;

public class CustomerServices {
	private User user;
	private ArrayList<Account> accounts;
	private ArrayList<String> accountTypes;
	static AccountDAO dao = new AccountDAOImpl();

	public CustomerServices(User user) {
		super();
		this.user = user;
		accounts = user.getAccounts();
		accountTypes = dao.getAccountTypes();
	}

	private int selectAccount() {
		for (int i = 0; i < accounts.size(); i++) {
			System.out.println((i + 1) + ". " + accountTypes.get(accounts.get(i).getAccountType() - 1));
		}

		return SelectOptionController.selectOption(accounts.size()) - 1;
	}

	public void customerOptions() {

		while (true) {

			System.out.println();
			System.out.println("What would you like to do today?");
			System.out.println("1. Check Account Balance");
			System.out.println("2. Make a Deposit");
			System.out.println("3. Make a Withdrawal");
			System.out.println("4. Transfer Money");
			System.out.println("5. Open a New Account");
			System.out.println("6. Apply for loan");
			System.out.println("7. Close Account");
			System.out.println("8. Exit");

			int option = SelectOptionController.selectOption(8);

			switch (option) {
			case 1: {
				checkBalance();
				break;
			}

			case 2: {
				makeDeposit();
				break;
			}

			case 3: {
				makeWithdrawl();
				break;
			}
			case 4: {
				makeTransfer();
				break;
			}
			case 5: {
				break;
			}

			case 6: {

				break;
			}
			case 7: {

				break;
			}
			case 8: {
				System.out.println("GoodBye " + user.getUsername() + ".");
				System.out.println("Have a nice day!");
				return;
			}

			}
		}
	}

	public void checkBalance() {
		System.out.println("Which account would you like to the balance of?");
		int choice = selectAccount();
		Account account = accounts.get(choice);
		System.out.println("Your account balance is : $" + account.getBalance());
		return;
	}

	public void makeDeposit() {
		System.out.println("Which account would you like to deposit money into?");
		int choice = selectAccount();
		Account account = accounts.get(choice);

		System.out.println("How much money would you like to deposit?");
		double amount = DWController.getAmount();
		Transaction transaction = new Transaction(-1, -1, amount, account.getAccountNum(), account.getAccountType());

		if (!transaction.storeTransaction()) {
			System.out.println("Something went wrong!");
			return;
		}

		double newBalance = account.getBalance() + amount;
		account.setBalance(newBalance);
		return;
	}

	public void makeWithdrawl() {
		System.out.println("Which acount would you like to withdraw money from?");
		int choice = selectAccount();
		Account account = accounts.get(choice);

		if (account.getMinBalance() == account.getBalance()) {
			System.out.println("There is no money in this account to withdraw.");
			return;
		}

		double amount;
		double newBalance;
		System.out.println("How much money would you like to withdraw?");

		do {
			amount = DWController.getAmount();
			newBalance = account.getBalance() - amount;
			if (newBalance < account.getMinBalance()) {
				System.out.println("Amount can not be greater than $" + (account.getBalance() - account.getMinBalance())
						+ " or else your account will fall below the minimum balance of $" + account.getMinBalance());
			}

		} while (newBalance < account.getMinBalance());

		Transaction transaction = new Transaction(account.getAccountNum(), account.getAccountType(), amount, -1, -1);

		if (!transaction.storeTransaction()) {
			System.out.println("Something went wrong!");
			return;
		}

		account.setBalance(newBalance);
		return;
	}

	public void makeTransfer() {
		System.out.println("Which account would you like to transfer money from?");
		int choice = selectAccount();
		Account account = accounts.get(choice);

		if (account.getMinBalance() == account.getBalance()) {
			System.out.println("There is no money in this account to transfer.");
			return;
		}

		System.out.println("Which account would you like to transfer money to?");

		for (int i = 0; i < accounts.size(); i++) {
			System.out.println((i + 1) + ". " + accountTypes.get(accounts.get(i).getAccountType() - 1));
		}

		System.out.println(accounts.size() + 1 + ". Other User's Account.");
		System.out.println(accounts.size() + 2 + ". External Account.");

		choice = SelectOptionController.selectOption(accounts.size() + 2);

		int accountNum;
		Account account2;

		if (choice < accounts.size() + 1) {
			account2 = accounts.get(choice - 1);
		} else if (choice == accounts.size() + 1) {
			System.out.println("Enter the number of the account that you would like transfer money to.");
			accountNum = TransferController.inputNum();
			try {
				account2 = dao.getAccountsByAccount_id(accountNum).get(0);
			} catch (IndexOutOfBoundsException e) {
				System.out.println();
				System.out.println("There is no account with that account number.");
				return;
			}

		} else {
			System.out.println("Enter the routing number of the account that you would like transfer money to.");
			int routingNum = TransferController.inputNum();
			System.out.println("Enter the number of the account that you would like transfer money to.");
			accountNum = TransferController.inputNum();
			account2 = null;
		}

		double amount;
		double newBalance;
		System.out.println("How much money would you like transfer?");

		do {
			amount = DWController.getAmount();
			newBalance = account.getBalance() - amount;
			if (newBalance < account.getMinBalance()) {
				System.out.println("Amount can not be greater than $" + (account.getBalance() - account.getMinBalance())
						+ " or else your account will fall below the minimum balance of $" + account.getMinBalance());
			}

		} while (newBalance < account.getMinBalance());

		Transaction transaction;

		if (account2 != null) {
			transaction = new Transaction(account.getAccountNum(), account.getAccountType(), amount,
					account2.getAccountNum(), account2.getAccountType());
		} else {
			transaction = new Transaction(account.getAccountNum(), account.getAccountType(), amount, -1, -1);
		}

		if (!transaction.storeTransaction()) {
			System.out.println("Something went wrong!");
			return;
		}

		account.setBalance(account.getBalance() - amount);
		if(account2!=null) {
			account2.setBalance(account2.getBalance() + amount);
		}
		return;
	}

}
