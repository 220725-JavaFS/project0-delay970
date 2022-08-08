package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public boolean storeAccount(Account account) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "insert into accounts(account_id, account_type, min_balance, approved) values (?,?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, account.getAccountNum());
			statement.setInt(2, account.getAccountType());
			statement.setDouble(3, account.getMinBalance());
			statement.setBoolean(4, account.isApproved());
			statement.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ArrayList<Account> getAccountsByAccount_id(int account_id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * from accounts where account_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, account_id);
			ResultSet result = statement.executeQuery();

			ArrayList<Account> accounts = new ArrayList<Account>();

			while (result.next()) {
				Account account = new Account(account_id, result.getInt("account_type"),
						result.getDouble("min_balance"), result.getBoolean("approved"));
				account.setBalance(getAccountBalance(account_id, account.getAccountType()));
				accounts.add(account);
			}
			return accounts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<String> getAccountTypes() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT account_type_name from account_type_codes;";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			ArrayList<String> codes = new ArrayList<String>();

			while (result.next()) {
				codes.add(result.getString("account_type_name"));
			}
			return codes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean storeTransaction(Transaction transaction) {

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "insert into transactions(from_account, from_account_type, amount, to_account, to_account_type) values (?,?,?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);

			if (transaction.getFromAcount() == -1) {
				statement.setNull(1, Types.INTEGER);
				statement.setNull(2, Types.INTEGER);
			} else {
				statement.setInt(1, transaction.getFromAcount());
				statement.setInt(2, transaction.getFromAcountType());
			}

			statement.setDouble(3, transaction.getAmount());

			if (transaction.getToAcount() == -1) {
				statement.setNull(4, Types.INTEGER);
				statement.setNull(5, Types.INTEGER);
			} else {
				statement.setInt(4, transaction.getToAcount());
				statement.setInt(5, transaction.getToAcountType());
			}

			statement.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public double getAccountBalance(int account, int account_type) {

		try (Connection conn = ConnectionUtil.getConnection()) {
			double balance = -1;

			String sql = "select COALESCE(SUM(amount),0) AS total from transactions where to_account = ? and to_account_type = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, account);
			statement.setInt(2, account_type);

			ResultSet result = statement.executeQuery();
			if (result.next()) {
				balance = result.getInt("total");
			}

			sql = "select COALESCE(SUM(amount),0) AS total from transactions where from_account = ? and from_account_type = ?;";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, account);
			statement.setInt(2, account_type);

			result = statement.executeQuery();
			if (result.next()) {
				balance -= result.getInt("total");
			}
			return balance;

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public boolean deleteAccount(Account account) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "delete from accounts where account_id = ? and account_type = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, account.getAccountNum());
			statement.setInt(2, account.getAccountType());
			statement.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ArrayList<Transaction> getTransactions(int account_id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * from transactions where to_account = ? or from_account = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, account_id);
			statement.setInt(2, account_id);
			ResultSet result = statement.executeQuery();

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();

			while (result.next()) {
				Transaction transaction = new Transaction(result.getInt("from_account"),result.getInt("from_account_type"), result.getDouble("amount"), result.getInt("to_account"), result.getInt("to_account_type"));
				transactions.add(transaction);
			}
			return transactions;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
