package io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking;

import org.bukkit.OfflinePlayer;

public class BankAccount {

	private double balance;
	private long bankID;
	private String name;
	private long accountHolderID;

	public BankAccount(long bankID, double balance, String name, long accountHolderID) {
		this.bankID = bankID;
		this.balance = balance;
		this.name = name;
		this.accountHolderID = accountHolderID;
	}
	
	public BankAccount(OfflinePlayer player, long bankID, double balance, String name) {
		this.balance = balance;
		this.bankID = bankID;
		this.name = name;
		
	}

	public double getBalance() {
		return balance;
	}

	public long getBankID() {
		return bankID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAccountHolderID(long accountHolderID) {
		this.accountHolderID = accountHolderID;
	}

	public long getAccountHolderID() {
		return accountHolderID;
	}

}
