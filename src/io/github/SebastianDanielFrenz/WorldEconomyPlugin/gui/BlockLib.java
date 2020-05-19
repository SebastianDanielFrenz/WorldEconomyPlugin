package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import java.sql.SQLException;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Company;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Corporation;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.PrivateCompany;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.Bank;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;

public class BlockLib {

	public static final Material PLAYER = Material.SKULL_ITEM;

	@SuppressWarnings("deprecation")
	public static ItemStack player(String name) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(name);
		meta.setDisplayName(name);
		skull.setItemMeta(meta);

		return skull;
	}

	@SuppressWarnings("deprecation")
	public static ItemStack player(OfflinePlayer player) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(player.getName());
		meta.setDisplayName(player.getName());
		skull.setItemMeta(meta);

		return skull;
	}

	public static ItemStack ai(String displayName) {
		return WEGUI.mkItem(Material.COMMAND, displayName);
	}

	public static ItemStack company(Company company) {
		if (company instanceof PrivateCompany) {
			return WEGUI.mkItem(Material.IRON_BLOCK, company.companyName);
		} else if (company instanceof Corporation) {
			return WEGUI.mkItem(Material.EMERALD_BLOCK, company.companyName);
		} else {
			return WEGUI.mkItem(Material.BARRIER, company.companyName, new String[] { "§4Invalid company type!" });
		}
	}

	public static ItemStack bank(Bank bank) {
		return WEGUI.mkItem(Material.WOOL, 1, 13, bank.name);
	}

	public static ItemStack bank_account(BankAccount bank_account) {
		try {
			return WEGUI.mkItem(Material.WOOL, 1, 5, bank_account.getName(),
					new String[] { "§f" + WEDB.getBank(bank_account.getBankID()).name, String.valueOf(bank_account.getBalance()) });
		} catch (SQLException e) {
			e.printStackTrace();
			return WEGUI.mkItem(Material.BARRIER, bank_account.getName(),
					new String[] { "§4This bank does not exist!", String.valueOf(bank_account.getBalance()) });
		}
	}

	public static final Material BUY = Material.WOOL; // yellow wool
	public static final Material SELL = Material.WOOL; // red wool

}
