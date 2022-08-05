package com.revature.models;

import java.util.Objects;

public class Transaction {
	private int id;
	private int toAcount;
	private int fromAcount;
	private int amount;
	
	public Transaction(int toAcount, int fromAcount, int amount) {
		super();
		this.toAcount = toAcount;
		this.fromAcount = fromAcount;
		this.amount = amount;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
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
