package com.revature.models;

public class Loan extends Account{
	private int apr;
	private int months;
	private double monthlyPayment;
	
	public Loan(int userid, int accountType, double balance, int apr, int months) {
		super(userid, accountType, balance, false);
		this.apr = apr;
		this.months = months;
		setMonthlyPayment();
	}
	
	public int getApr() {
		return apr;
	}
	
	public void setApr(int apr) {
		this.apr = apr;
	}
	
	public int getMonths() {
		return months;
	}
	
	public void setMonths(int months) {
		this.months = months;
	}
	
	public double getMonthlyPayment() {
		return monthlyPayment;
	}
	
	private void setMonthlyPayment() {
		double r = apr/12;
		monthlyPayment = getBalance()/(Math.pow((r+1), 12)-1)/(r*Math.pow((r+1), 12));
	}
	
}
