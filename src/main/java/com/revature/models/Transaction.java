package com.revature.models;

import java.util.Objects;

import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOImpl;

public class Transaction {
	private int id;
	private int fromAcount;
	private int fromAcountType;
	private double amount;
	private int toAcount;
	private int toAcountType;
	
	public Transaction(int fromAcount, int fromAcountType, double amount, int toAcount, int toAcountType) {
		super();
		this.fromAcount = fromAcount;
		this.fromAcountType = fromAcountType;
		this.amount = amount;
		this.toAcount = toAcount;
		this.toAcountType = toAcountType;
	}

	public boolean storeTransaction() {
		AccountDAO dao = new AccountDAOImpl();
		return dao.storeTransaction(this);
	}
	
	@Override
	public String toString() {
		return "Transaction [fromAcount=" + fromAcount + ", fromAcountType=" + fromAcountType + ", amount=" + amount
				+ ", toAcount=" + toAcount + ", toAcountType=" + toAcountType + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFromAcountType() {
		return fromAcountType;
	}

	public void setFromAcountType(int fromAcountType) {
		this.fromAcountType = fromAcountType;
	}

	public int getToAcountType() {
		return toAcountType;
	}

	public void setToAcountType(int toAcountType) {
		this.toAcountType = toAcountType;
	}

	public int getToAcount() {
		return toAcount;
	}

	public void setToAcount(int toAcount) {
		this.toAcount = toAcount;
	}

	public int getFromAcount() {
		return fromAcount;
	}

	public void setFromAcount(int fromAcount) {
		this.fromAcount = fromAcount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return id == other.id;
	}

}
