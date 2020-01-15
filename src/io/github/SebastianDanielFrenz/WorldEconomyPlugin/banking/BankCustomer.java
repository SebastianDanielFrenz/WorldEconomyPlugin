package io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking;

import java.util.ArrayList;

public abstract class BankCustomer {

	ArrayList<BankAccount> accounts;
	public final Bank bank;

	public BankCustomer(Bank bank, ArrayList<BankAccount> accounts) {
		this.bank = bank;
		this.accounts = accounts;
	}

	public void createAccount(Bank bank, String name) {
		bank.createAccount(this, name);
	}

	public double getBalance(String name) {
		for (BankAccount account:accounts) {
			if (account.getName().equals(name)) {
				return bank.getBalance(account);
			}
		}
	}

}
