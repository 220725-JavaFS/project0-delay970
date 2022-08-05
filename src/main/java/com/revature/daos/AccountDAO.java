package com.revature.daos;

import com.revature.models.Account;
import com.revature.models.User;
import java.util.ArrayList;

public interface AccountDAO {	
	public ArrayList<Account> getAccountsByAccount_id(int account_id);
}
