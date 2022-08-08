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
	private ArrayList<String> loanTypes;
	private int numAccounts = 4;
	static AccountDAO dao = new AccountDAOImpl();

	public CustomerServices(User user) {
		super();
		this.user = user;
		accounts = user.getAccounts();
		accountTypes = dao.getAccountTypes();
		loanTypes = new ArrayList<String>();
		for (int i = accountTypes.size(); i > numAccounts; i--) {
			loanTypes.add(accountTypes.get(i - 1));
			accountTypes.remove(i - 1);
		}
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
				openAccount();
				break;
			}

			case 6: {
				loanApply();
				break;
			}
			case 7: {
				closeAccount();
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

		if (account.getAccountType() == 4) {
			System.out.println("You can't withdraw money from a CD until it has matured.");
			return;
		}

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

		if (account.getAccountType() == 4) {
			System.out.println("You can't transfer money from a CD until it has matured.");
			return;
		}

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

		if (account.equals(account2)) {
			System.out.println("You can't transfer money to the same account that you are transfering money from.");
			return;
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
		if (account2 != null) {
			account2.setBalance(account2.getBalance() + amount);
		}
		return;
	}

	public void openAccount() {

		ArrayList<Integer> avalibleAccounts = new ArrayList<Integer>();

		outer: for (int i = 0; i < accountTypes.size(); i++) {
			for (Account account : accounts) {
				if (account.getAccountType() == i + 1) {
					continue outer;
				}
			}
			avalibleAccounts.add(i);
		}

		System.out.println("Which type of account would you like to open?");
		for (int i = 0; i < avalibleAccounts.size(); i++) {
			System.out.println((i + 1) + ". " + accountTypes.get(avalibleAccounts.get(i)));
		}

		int accountType = avalibleAccounts.get(SelectOptionController.selectOption(avalibleAccounts.size()) - 1) + 1;

		double minBalance = 0;

		switch (accountType) {
		case 3:
			System.out.println("The minimum balance for a " + accountTypes.get(accountType - 1) + " is $5000.");
			minBalance = 5000;
			break;
		case 4:
			System.out.println("The minimum balance for a " + accountTypes.get(accountType - 1) + " is $10000.");
			minBalance = 10000;
			break;
		}

		Account newAccount = new Account(accounts.get(0).getAccountNum(), accountType, minBalance, true);

		if (!newAccount.storeAccount()) {
			System.out.println("Something went wrong.");
			System.out.println("Try again later.");
			return;
		}

		System.out.println();
		double amount;
		do {
			System.out.println("Enter you initial deposit:");
			amount = DWController.getAmount();
			if (amount < newAccount.getMinBalance()) {
				System.out.println(
						"Your initial deposit must be larger than the minimum required balance of you account.");
			}
		} while (amount < newAccount.getMinBalance());

		Transaction transaction = new Transaction(-1, -1, amount, newAccount.getAccountNum(),
				newAccount.getAccountType());

		if (!transaction.storeTransaction()) {
			System.out.println("Something went wrong.");
			System.out.println("Try again later.");
			return;
		}

		newAccount.setBalance(amount);
		accounts.add(newAccount);
	}

	public void loanApply() {
		System.out.println();
	}
	
	public void closeAccount() {
		if (accounts.size() == 1) {
			System.out.println("You can't close your last account.");
			return;
		}

		System.out.println("Which account would you like to close?");
		int choice = selectAccount();
		Account account = accounts.get(choice);

		if (account.getAccountType() == 4) {
			System.out.println("You can't close a " + accountTypes.get(3) + " until it has matured.");
			return;
		}

		if (account.getBalance() > 0) {

			Account account2;

			do {
				System.out.println();
				System.out.println("Choose a differnt account to transfer your remaining balance into.");

				choice = selectAccount();
				account2 = accounts.get(choice);

				if (account.equals(account2)) {
					System.out.println("You can't transfer money into the same account that you are closeing.");
				}
			} while (account.equals(account2));

			Transaction transaction = new Transaction(account.getAccountNum(), account.getAccountType(),
					account.getBalance(), account2.getAccountNum(), account2.getAccountType());

			if (!transaction.storeTransaction()) {
				System.out.println("Something went wrong.");
				System.out.println("Try again later.");
				return;
			}
		}

		if (!account.deleteAccount()) {
			System.out.println("Something went wrong.");
			System.out.println("Try again later.");
			return;
		}

		accounts.remove(account);
	}

}
