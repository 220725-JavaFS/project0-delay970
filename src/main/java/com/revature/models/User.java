package com.revature.models;

import java.util.ArrayList;

public class User {
	
	private String username;
	private String password;
	private int permissions;
	private ArrayList<Account> accounts = new ArrayList<Account>();
	
	public User() {
		super();
	}

	public User(String username, String password, int permissions,  ArrayList<Account> accounts ) {
		super();
		this.username = username;
		this.password = password;
		this.permissions = permissions;
		this.accounts = accounts;
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
	
	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", permissions=" + permissions + "]";
	}
	

}
