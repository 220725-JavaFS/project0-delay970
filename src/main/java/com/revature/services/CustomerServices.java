package com.revature.services;

import com.revature.models.User;
import com.revature.repos.AccountRepo;
import com.revature.repos.AccountTypeRepo;
import com.revature.controllers.DWController;
import com.revature.controllers.SelectOptionController;

import java.util.ArrayList;

import com.revature.models.Account;

public class CustomerServices {
	private User user;
	private ArrayList<Account> accounts;
	
	public CustomerServices(User user) {
		super();
		this.user = user;
		accounts = AccountRepo.getAccountsByName(user.getUsername());
	}
	
	private int selectAccount() {		
		for(int i = 0; i<accounts.size(); i++) {
			System.out.println((i+1) +". " + AccountTypeRepo.getAccountNameByCode(accounts.get(i).getAccountType()));
		}
		
		return SelectOptionController.selectOption(accounts.size())-1;
	}
	
	public void customerOptions() {
		
		while(true) {
			
		
		System.out.println();
		System.out.println("What would you like to do today?");
		System.out.println("1. Check Account Balance");
		System.out.println("2. Make a Deposit");
		System.out.println("3. Make a Withdrawal");
		System.out.println("4. Transfer Money");
		System.out.println("5. Open a New Account");
		System.out.println("6. Close Account");
		System.out.println("7. Exit");
		
		int option = SelectOptionController.selectOption(7);
		
		
		switch(option) {
		case 1:{
			System.out.println("Which account would you like to the balance of?");
			int choice = selectAccount();
			Account account = accounts.get(choice);
			double balance = checkBalance(account);
			System.out.println("Your account balance is : $"+balance);
			break;
		}
			
		case 2:{
			System.out.println("Which account would you like to deposit money into?");
			int choice = selectAccount();
			Account account = accounts.get(choice);
			makeDeposit(account);
			break;
		}
			
		case 3:{
			System.out.println("Which acount would you like to withdraw money from?");
			int choice = selectAccount();
			Account account = accounts.get(choice);
			makeWithdrawl(account);
			break;
		}
		case 4:{
			
			break;
		}
		case 5:{
						
			break;
		}

		case 6:{
			
			break;
		}
		case 7:{
			System.out.println("GoodBye");
			return;
		}
			
		}
		}
	}
	
	public double checkBalance(Account account) { 
		return account.getBalance();
	}
	
	public void makeDeposit(Account account) {
		System.out.println("How much money would you like to deposit?");
		double amount = DWController.getAmount();
		double newBalance = account.getBalance() + amount;
		account.setBalance(newBalance);
		AccountRepo.updateAccount(account);
		return;
	}
	
	public void makeWithdrawl(Account account) {
		
		if(account.getBalance() == 0) {
			System.out.println("There is no money in this acount to withdraw.");
			return;
		}
		double newBalance;
		System.out.println("How much money would you like to withdraw?");
		do {
			double amount = DWController.getAmount();
			newBalance = account.getBalance() - amount;
			if(newBalance<0) {
				System.out.println("Amount can not be greater than available account balance.");
			}
		}while(newBalance<0);
		account.setBalance(newBalance);
		AccountRepo.updateAccount(account);
		return;
	}
}
