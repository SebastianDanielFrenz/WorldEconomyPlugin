package io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking;

import java.util.ArrayList;

public class BankAccount {

	private double balance;
	private Bank bank;
	private ArrayList<BankCustomer> customers;
	private String name;

	public BankAccount(Bank bank, ArrayList<BankCustomer> customers, double balance, String name) {
		this.bank = bank;
		this.customers = customers;
		this.balance = balance;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
