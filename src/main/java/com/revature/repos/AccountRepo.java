package com.revature.repos;

import java.util.ArrayList;

import com.revature.models.Account;
import com.revature.models.User;

public class AccountRepo {
	
	public static ArrayList<Account> accounts = new ArrayList<Account>();
	
	public static void storeAccount(Account account) {
		if(!accounts.contains(account)) {
			accounts.add(account);
		}
		return;
	}
	
	public static ArrayList<Account> getAccountsByName(String name) {
		ArrayList<Account> userAccounts = new ArrayList<Account>();
		for(Account account: accounts) {
			if(account.getUsername().equals(name)) {
				userAccounts.add(account);
			}
		}
		return userAccounts;
	}
	
	public static void updateAccount(Account account) {
		for(int i =0; i<accounts.size(); i++) {
			if(accounts.get(i).equals(account)) {
				accounts.set(i, account);
				return;
			}
		}
		
		System.out.println("Could not update acount value.");
	}
}
