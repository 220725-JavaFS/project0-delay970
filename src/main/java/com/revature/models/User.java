package com.revature.models;

import com.revature.repos.AccountRepo;

public class User {
	
	private String username;
	private String password;
	private int permissions;
	
	public User() {
		super();
		createStartingAccounts();
	}

	public User(String username, String password, int permissions) {
		super();
		this.username = username;
		this.password = password;
		this.permissions = permissions;
		createStartingAccounts();
	}

	private void createStartingAccounts() {
		Account checking = new Account(username, 0);
		Account savings = new Account(username, 1);
		
		AccountRepo.storeAccount(checking);
		AccountRepo.storeAccount(savings);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPermissions() {
		return permissions;
	}

	public void setPermissions(int permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", permissions=" + permissions + "]";
	}

}
