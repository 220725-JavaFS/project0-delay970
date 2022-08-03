package com.revature;

import com.revature.controllers.WelcomeController;
import com.revature.files.OutFile;
import com.revature.controllers.SelectOptionController;
import com.revature.models.User;
import com.revature.repos.AccountRepo;
import com.revature.repos.UserRepo;
import com.revature.services.UserOptions;

public class Driver {
	User user;
	public static void main(String[] args) {
		System.out.println("Welcome to Fox banking.");
		
		char n = 'a';
		
		for(int i =0; i<10; i++) {
			String name = "" + n++;
			name.trim();
			User A = new User(name, name, 0);
			UserRepo.storeUser(A);
		}
		
		//User activeUser = WelcomeController.welcome();
		User activeUser = UserRepo.users.get(0);
		
		UserOptions options = new UserOptions(activeUser);
		options.displayUserOptions();
		
//		OutFile out = new OutFile();
//		
////		out.toFile("Customer.txt", UserRepo.users);
////		
////		out.toFile("Accounts.txt", AccountRepo.accounts);
	}

}
