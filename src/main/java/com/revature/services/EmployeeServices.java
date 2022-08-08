package com.revature.services;

import java.util.ArrayList;

import com.revature.controllers.SelectOptionController;
import com.revature.controllers.TransferController;
import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOImpl;
import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.User;

public class EmployeeServices {

	User user;
	static AccountDAO dao = new AccountDAOImpl();
	private ArrayList<String> accountTypes;
	
	public EmployeeServices(User user) {
		super();
		this.user = user;
		accountTypes = dao.getAccountTypes();
	}
	
	public void employeeOptions() {

		while (true) {

			System.out.println();
			System.out.println("What would you like to do today?");
			System.out.println("1. Check Account Balance");
			System.out.println("2. Print Account Statment");
			System.out.println("3. Approve Loan");
			System.out.println("4. Exit");

			int option = SelectOptionController.selectOption(4);

			switch (option) {
			case 1: {
				checkBalance();
				break;
			}

			case 2: {
				printStatment();
				break;
			}

			case 3: {
				// makeWithdrawl();
				break;
			}

			case 4: {
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
		
		for(Account account: accounts) {
			System.out.println();
			System.out.println("Account Number: "+account.getAccountNum());
			System.out.println("Account Type: "+accountTypes.get(account.getAccountType()-1));
			System.out.println("Account Balance: $"+account.getBalance());
			
		}
	}
	
	public void printStatment() {
		System.out.println("Enter the account that you would like to print a statment for.");
		
		int accountNum = TransferController.inputNum();
		ArrayList<Transaction> transactions = dao.getTransactions(accountNum);
		
		if(transactions == null) {
			System.out.println("There are no transactions associated with this account number.");
			return;
		}
		
		for(Transaction transaction: transactions) {
			System.out.println(transaction);
		}
	}
}
