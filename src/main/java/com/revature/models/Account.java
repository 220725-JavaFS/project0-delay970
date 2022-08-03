package com.revature.models;

import java.util.Objects;

public class Account {
	private String username;
	private int accountType;
	private double balance;
	
	public Account() {
		super();
	}
	
	public Account(String username, int accountType) {
		super();
		this.username = username;
		this.accountType = accountType;
		this.balance = 0;
	}
	
	public Account(String username, int accountType, double balance) {
		super();
		this.username = username;
		this.accountType = accountType;
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Account [username=" + username + ", accountType=" + accountType + ", balance="
				+ balance + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountType, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accountType == other.accountType && Objects.equals(username, other.username);
	}
	
	
}
