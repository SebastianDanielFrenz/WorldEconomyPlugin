package io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking;

import java.util.ArrayList;

public class BankAccount {

	private double balance;
	private Bank bank;
	private ArrayList<BankCustomer> customers;

	public BankAccount(Bank bank, ArrayList<BankCustomer> customers, double balance) {
		this.bank = bank;
		this.customers = customers;
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public ArrayList<BankCustomer> getCustomers() {
		return customers;
	}

	public Bank getBank() {
		return bank;
	}

}
