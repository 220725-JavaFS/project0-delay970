package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean storeUser(User user) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "insert into users(user_id, pass, user_permissions) values (?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getPermissions());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getUserByUsername(String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * from users where user_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();

			AccountDAOImpl dao = new AccountDAOImpl();

			if (result.next()) {
				User user = new User(result.getString("user_id"), result.getString("pass"),
						result.getInt("user_permissions"), dao.getAccountsByAccount_id(result.getInt("account_id")));
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUserByAccount_id(int account_id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * from users where account_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, account_id);
			ResultSet result = statement.executeQuery();

			AccountDAOImpl dao = new AccountDAOImpl();

			if (result.next()) {
				User user = new User(result.getString("user_id"), result.getString("pass"),
						result.getInt("user_permissions"), dao.getAccountsByAccount_id(result.getInt("account_id")));
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<String> getPermissionTypes() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT permission_name from permission_codes;";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			ArrayList<String> codes = new ArrayList<String>();

			while (result.next()) {
				codes.add(result.getString("permission_name"));
			}
			return codes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getUserAccount_id(String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT account_id from users where user_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				int account_id = result.getInt("account_id");
				return account_id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
