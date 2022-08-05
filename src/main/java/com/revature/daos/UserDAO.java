package com.revature.daos;

import com.revature.models.User;

public interface UserDAO {
	public void storeUser(User user);
	
	public User getUserByUsername(String username);
	
	public User getUserByAccount_id(int account_id);
}
