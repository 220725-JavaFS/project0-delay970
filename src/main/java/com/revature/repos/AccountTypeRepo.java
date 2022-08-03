package com.revature.repos;

public class AccountTypeRepo {
	final private static String accountName[] = {"Checking", "Savings"};

	public static String getAccountNameByCode(int code) {
		return accountName[code];
	}
}
