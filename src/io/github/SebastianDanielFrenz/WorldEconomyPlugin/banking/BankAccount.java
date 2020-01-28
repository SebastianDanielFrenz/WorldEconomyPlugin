package io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking;

import java.sql.SQLException;

import org.bukkit.OfflinePlayer;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Company;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

public class BankAccount {

	private double balance;
	private long bankID;
	private String name;
	private long accountHolderID;
	private String type;
	private long ID;

	public BankAccount(long ID, long bankID, double balance, String name, long accountHolderID, String type) {
		this.bankID = bankID;
		this.balance = balance;
		this.name = name;
		this.accountHolderID = accountHolderID;
		this.setType(type);
		this.ID = ID;
	}

	public BankAccount(long ID, OfflinePlayer player, long bankID, double balance, String name) throws SQLException {
		this.balance = balance;
		this.bankID = bankID;
		this.name = name;
		accountHolderID = WorldEconomyPlugin.getUserProfile(player).bankingID;
		type = "player";
		this.ID = ID;
	}

	public BankAccount(long ID, Company company, long bankID, double balance, String name) throws SQLException {
		this.balance = balance;
		this.bankID = bankID;
		this.name = name;
		accountHolderID = company.bankingID;
		type = "company";
		this.ID = ID;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

}
