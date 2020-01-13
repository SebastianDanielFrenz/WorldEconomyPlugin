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

}
