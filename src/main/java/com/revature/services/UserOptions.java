package com.revature.services;

import com.revature.controllers.SelectOptionController;
import com.revature.models.User;

public class UserOptions {

	private User user;
	
	public UserOptions() {
		super();
	}
	
	public UserOptions(User user) {
		super();
		this.user = user;
	}
	
	public void displayUserOptions() {
		
		
		System.out.println("Hello " + user.getUsername() +"!");
		
		
		switch(user.getPermissions()) {
		case 1:
			
			CustomerServices services = new CustomerServices(user);
			services.customerOptions();
			return;
		case 2:
			
			return;
		case 3:
			
			return;
			
		default:
			System.out.println("Error invlaid user permissions");
			return;
		}
	}
	
	
}
