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

		System.out.println("Hello " + user.getUsername() + "!");

		switch (user.getPermissions()) {
		case 1:
			
			CustomerServices cservices = new CustomerServices(user);
			cservices.customerOptions();
			return;
		case 2:
			EmployeeServices eservices = new EmployeeServices(user);
			eservices.employeeOptions();
			return;
		case 3:
			AdminServices aservices = new AdminServices(user);
			aservices.adminOptions();
			return;

		default:
			System.out.println("Error invlaid user permissions");
			return;
		}
	}
}
