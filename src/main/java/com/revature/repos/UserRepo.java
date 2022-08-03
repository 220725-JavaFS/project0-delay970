package com.revature.repos;

import java.util.ArrayList;

import com.revature.models.User;

public class UserRepo {
	
	public static ArrayList<User> users = new ArrayList<User>();
	
	public static void storeUser(User user) {
		if(!users.contains(user)) {
			users.add(user);
		}
		return;
	}
	
	public static User getUserByName(String name) {
		for(User user: users) {
			if(user.getUsername().equals(name)) {
				return user;
			}
		}
		return null;
	}
}
