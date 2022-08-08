package com.revature.daos;

import com.revature.models.Account;
import com.revature.models.Transaction;
import java.util.ArrayList;

public interface AccountDAO {
	public boolean storeAccount(Account account);
	
	public ArrayList<Account> getAccountsByAccount_id(int account_id);

	public ArrayList<String> getAccountTypes();
	
	public boolean storeTransaction(Transaction transaction);
	
	public double getAccountBalance(int account, int account_type);
	
}
