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
		System.out.println("Welcome to Fox Banking.");

		UserDAOImpl dbo = new UserDAOImpl();

		// CreateFakeUserData.generateUsers(20, 1);
		// CreateFakeUserData.generateUsers(20, 2);
		// CreateFakeUserData.generateUsers(20, 3);

		User activeUser = WelcomeController.welcome();

		User testCustomer = dbo.getUserByAccount_id(174);
		User testEmployee = dbo.getUserByAccount_id(134);
		User testAdmin = dbo.getUserByAccount_id(154);

		UserOptions options = new UserOptions(activeUser);
		options.displayUserOptions();
		
		
		activeUser = WelcomeController.welcome();
		options = new UserOptions(activeUser);
		options.displayUserOptions();
		
		System.out.println("Demo Employee:");
		activeUser = testEmployee;
		options = new UserOptions(activeUser);
		options.displayUserOptions();
		
		System.out.println("Demo Admin");
		activeUser = testAdmin;
		options = new UserOptions(activeUser);
		options.displayUserOptions();
	}

}
