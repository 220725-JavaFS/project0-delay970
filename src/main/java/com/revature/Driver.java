package com.revature;

import com.revature.controllers.WelcomeController;
import com.revature.daos.UserDAOImpl;
import com.revature.data.CreateFakeUserData;
import com.revature.controllers.SelectOptionController;
import com.revature.models.User;
import com.revature.services.UserOptions;

public class Driver {
	User user;
	public static void main(String[] args) {
		System.out.println("Welcome to Fox banking.");
		
		UserDAOImpl dbo = new UserDAOImpl();
		
		//CreateFakeUserData.generateUsers(20, 1);
		//CreateFakeUserData.generateUsers(20, 2);
		//CreateFakeUserData.generateUsers(20, 3);
		
		//User activeUser = WelcomeController.welcome();
		
		User testCustomer = dbo.getUserByAccount_id(51);
		User testEmployee = dbo.getUserByAccount_id(71);
		User testAdmin = dbo.getUserByAccount_id(91);
		User activeUser = testCustomer;
		
		UserOptions options = new UserOptions(activeUser);
		options.displayUserOptions();
		
//		OutFile out = new OutFile();
//		
////		out.toFile("Customer.txt", UserRepo.users);
////		
////		out.toFile("Accounts.txt", AccountRepo.accounts);
	}

}
