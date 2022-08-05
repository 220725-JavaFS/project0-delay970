package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO{

	@Override
	public ArrayList<Account> getAccountsByAccount_id(int account_id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * from accounts where account_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, account_id);
			ResultSet result = statement.executeQuery();
			
			ArrayList<Account> accounts = new ArrayList<Account>();
			
			while(result.next()) {
				Account account = new Account(
						result.getInt("account_id"),
						result.getInt("account_type"),
						result.getDouble("min_balance"),
						result.getBoolean("approved")
						);
				accounts.add(account);
			}
			return accounts;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
