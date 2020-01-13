package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.util.ArrayList;

import org.bukkit.OfflinePlayer;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankingProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.EmployeeProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.Contract;

public class WorldEconomyProfile {

	public final OfflinePlayer player;
	public final BankingProfile bankingProfile;
	public final EmployeeProfile employeeProfile;
	public final ArrayList<Contract> other_contracts;

	public WorldEconomyProfile(OfflinePlayer player, BankingProfile bankingProfile, EmployeeProfile employeeProfile,
			ArrayList<Contract> other_contracts) {
		this.player = player;
		this.bankingProfile = bankingProfile;
		this.employeeProfile = employeeProfile;
		this.other_contracts = other_contracts;
	}

}
