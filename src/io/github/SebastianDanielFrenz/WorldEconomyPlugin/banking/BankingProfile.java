package io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking;

import java.util.ArrayList;

import org.bukkit.OfflinePlayer;

public class BankingProfile {

	OfflinePlayer player;
	ArrayList<BankCustomer> bankCustomers;

	public BankingProfile(OfflinePlayer player, ArrayList<BankCustomer> bankCustomers) {
		this.player = player;
		this.bankCustomers = bankCustomers;
	}

	public BankCustomer getBankCustomer(Bank bank) {
		for (BankCustomer c : bankCustomers) {
			if (c.bank.equals(bank)) {
				return c;
			}
		}

		return null;
	}

	public void registerBankCustomer(BankCustomer customer) {
		bankCustomers.add(customer);
	}

}
