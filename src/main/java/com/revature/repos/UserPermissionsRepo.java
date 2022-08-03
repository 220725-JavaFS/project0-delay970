package com.revature.repos;

public class UserPermissionsRepo {
	final private static String permissionName[] = {"Customer", "Employee", "Admin"};

	public static String getAccountNameByCode(int code) {
		return permissionName[code];
	}
}
