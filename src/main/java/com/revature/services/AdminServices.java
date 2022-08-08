package com.revature.services;

import java.util.ArrayList;

import com.revature.controllers.DWController;
import com.revature.controllers.SelectOptionController;
import com.revature.controllers.TransferController;
import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOImpl;
import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.User;

public class AdminServices {
	User user;
	static AccountDAO dao = new AccountDAOImpl();
	private ArrayList<String> accountTypes;

	public AdminServices(User user) {
		super();
		this.user = user;
		accountTypes = dao.getAccountTypes();
	}

	private Account selectAccount() {
		int accountNum = TransferController.inputNum();
		ArrayList<Account> accounts = dao.getAccountsByAccount_id(accountNum);
		if (accounts == null) {
			System.out.println("There are no accounts associated with that account number.");
			return null;
		}

		System.out.println("Select the account type.");

		for (int i = 0; i < accounts.size(); i++) {
			System.out.println((i + 1) + ". " + accountTypes.get(accounts.get(i).getAccountType() - 1));

		}

		Account account = accounts.get(SelectOptionController.selectOption(accounts.size()) - 1);

		return account;
	}

	public void employeeOptions() {

		while (true) {

			System.out.println();
			System.out.println("What would you like to do today?");
			System.out.println("1. Check Account Balance");
			System.out.println("2. Add Money to an Account");
			System.out.println("3. Make a Withdrawal");
			System.out.println("4. Transfer Money");
			System.out.println("5. Fully Close Account");
			System.out.println("6. Exit");

			int option = SelectOptionController.selectOption(6);

			switch (option) {
			case 1: {
				checkBalance();
				break;
			}

			case 2: {
				addMoney();
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
				closeAccount();
				break;
			}
			case 6: {
				System.out.println("GoodBye " + user.getUsername() + ".");
				System.out.println("Have a nice day!");
				return;
			}

			}
		}
	}

	public void checkBalance() {
		System.out.println("Enter the number of the account that you would like to view the balance of.");
		int accountNum = TransferController.inputNum();

		ArrayList<Account> accounts = dao.getAccountsByAccount_id(accountNum);
		if (accounts == null) {
			System.out.println("There are no accounts associated with that account number.");
			return;
		}

		for (Account account : accounts) {
			System.out.println();
			System.out.println("Account Number: " + account.getAccountNum());
			System.out.println("Account Type: " + accountTypes.get(account.getAccountType() - 1));
			System.out.println("Account Balance: $" + account.getBalance());

		}
	}

	public void addMoney() {
		System.out.println("Enter the number of the account that you would like to add money to.");

		Account account = selectAccount();

		if (account == null) {
			return;
		}

		System.out.println();
		System.out.println("Enter the amount of money.");

		double amount = DWController.getAmount();

		Transaction transaction = new Transaction(-1, -1, amount, account.getAccountNum(), account.getAccountType());

		if (!transaction.storeTransaction()) {
			System.out.println("Something went wrong!");
			return;
		}

		double newBalance = account.getBalance() + amount;
		account.setBalance(newBalance);
	}

	public void makeWithdrawl() {
		System.out.println("Enter the number of the account that you would like to withdraw money from.");

		Account account = selectAccount();

		if (account == null) {
			return;
		}

		if (account.getBalance() == account.getMinBalance()) {
			System.out.println("There is no money avlaible in this account to withdraw.");
			return;
		}

		double newBalance;
		double amount;
		do {
			System.out.println();
			System.out.println("Enter the amount of money.");

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
	}
	
	public void makeTransfer() {
		System.out.println("Enter the account that you are transfering the money from. ");
		Account account = selectAccount();
		
		if(account == null) {
			return;
		}
		
		if(account.getBalance() == account.getMinBalance()) {
			System.out.println("There is no money in this account that can be transfered.");
			return;
		}
		
		System.out.println("Enter the account that you are sending money to.");
		Account account2 = selectAccount();
		
		if(account2 == null) {
			return;
		}
		
		if(account.equals(account2)) {
			System.out.println("You can't send money to the same account you are taking money from.");
			return;
		}
		
		double newBalance;
		double amount;
		do {
			System.out.println();
			System.out.println("Enter the amount of money.");

			amount = DWController.getAmount();
			newBalance = account.getBalance() - amount;
			if (newBalance < account.getMinBalance()) {
				System.out.println("Amount can not be greater than $" + (account.getBalance() - account.getMinBalance())
						+ " or else your account will fall below the minimum balance of $" + account.getMinBalance());
			}
		} while (newBalance < account.getMinBalance());

		Transaction transaction = new Transaction(account.getAccountNum(), account.getAccountType(), amount, account2.getAccountNum(), account2.getAccountType());

		if (!transaction.storeTransaction()) {
			System.out.println("Something went wrong!");
			return;
		}
	}
	
	public void closeAccount() {
		System.out.println("Enter the account that you would like to fully close.");
		
		int accountNum = TransferController.inputNum();

		ArrayList<Account> accounts = dao.getAccountsByAccount_id(accountNum);
		if (accounts == null) {
			System.out.println("There are no accounts associated with that account number.");
			return;
		}
		
		for(int i = accounts.size()-1; i > 0; i--) {
			Account account = accounts.get(i);
			if(account.getBalance() > 0) {
				double amount = account.getBalance();
				Transaction transaction = new Transaction(account.getAccountNum(), account.getAccountType(), amount, account.getAccountNum(), 1);
				if (!transaction.storeTransaction()) {
					System.out.println("Something went wrong!");
					continue;
				}
			}
			
			if (!account.deleteAccount()) {
				System.out.println("Something went wrong.");
				continue;
			}

			accounts.remove(account);
			
		}
		
		Account account = accounts.get(0);
		if(account.getBalance() >0) {
			System.out.println("The customer must withdraw all of there money before their final account can be closed.");
			return;
		}
		
		if (!account.deleteAccount()) {
			System.out.println("Something went wrong.");
		}

		accounts.remove(account);
	}
}
