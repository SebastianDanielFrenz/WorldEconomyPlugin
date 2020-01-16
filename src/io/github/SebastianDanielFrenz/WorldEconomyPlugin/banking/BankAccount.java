package io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking;

public class BankAccount {

	private double balance;
	private int bankID;
	private String name;

	public BankAccount(int bankID, double balance, String name) {
		this.bankID = bankID;
		this.balance = balance;
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public int getBankID() {
		return bankID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
