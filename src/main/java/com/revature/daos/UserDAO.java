package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.User;

public interface UserDAO {
	public boolean storeUser(User user);

	public User getUserByUsername(String username);

	public User getUserByAccount_id(int account_id);

	public ArrayList<String> getPermissionTypes();
	
	public int getUserAccount_id(String username);
}
