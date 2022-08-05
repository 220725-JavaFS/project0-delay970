package com.revature.data;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;

public class CreateFakeUserData {
	
	public static void generateUsers(int num, int privlage) {
		for(int i = 0; i< num; i++) {
			
			String username = "";
			for(int j =0; j<6; j++) {
				char c = (char)Math.floor(Math.random()*(122-97+1)+97);
				username+=c;
			}
			
			User user = new User(username, "password", privlage, null);
			UserDAOImpl dao = new UserDAOImpl();
			dao.storeUser(user);
		}
	}
}
