package com.revature.models;

import java.util.Objects;

import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOImpl;

public class Account {
	private int accountNum;
	private int accountType;
	private double balance;
	private double minBalance;
	private boolean approved = false;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int accountNum, int accountType, double minBalance, boolean approved) {
		super();
		this.accountNum = accountNum;
		this.accountType = accountType;
		this.minBalance = minBalance;
		this.approved = approved;
	}

	public boolean storeAccount() {
		AccountDAO dao = new AccountDAOImpl();
		return dao.storeAccount(this);
	}

	public boolean deleteAccount() {
		AccountDAO dao = new AccountDAOImpl();
		return dao.deleteAccount(this);
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public int getAccountNum() {
		return accountNum;
	}

	public int getAccountType() {
		return accountType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNum, accountType);
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
		return accountNum == other.accountNum && accountType == other.accountType;
	}

}
