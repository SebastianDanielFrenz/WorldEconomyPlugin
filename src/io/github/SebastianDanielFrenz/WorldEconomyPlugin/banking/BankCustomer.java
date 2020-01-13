package io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking;

import java.util.ArrayList;

public abstract class BankCustomer {

	ArrayList<BankAccount> accounts;
	Bank bank;

	public BankCustomer(Bank bank, ArrayList<BankAccount> accounts) {
		this.bank = bank;
		this.accounts = accounts;
	}

}
