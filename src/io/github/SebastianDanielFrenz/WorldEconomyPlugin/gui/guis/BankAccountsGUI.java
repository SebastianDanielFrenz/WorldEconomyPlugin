package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BlockLib;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class BankAccountsGUI extends WEGUI {

	public BankAccountsGUI(WEGUI parent, Player player) {
		super(parent, new GUIItem[] {}, "Bank Accounts");

		BankAccountsGUI out = this;

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		items.add(new GUIItem(0, 4, mkItem(Material.SIGN, "Bank Accounts")) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});

		try {
			List<BankAccount> bank_accounts = WEDB.getAllBankAccounts(player);
			for (BankAccount bank_account : bank_accounts) {
				items.add(new GUIItem(slot,
						mkItem(BlockLib.BANK_ACCOUNT, bank_account.getName(),
								new String[] { "§f" + WEDB.getBank(bank_account.getBankID()).name,
										String.valueOf(bank_account.getBalance()) })) {
					@Override
					public void event(InventoryClickEvent event) {
						new BankAccountGUI(out, bank_account).openInventory((Player) event.getWhoClicked());
					}
				});
				slot++;
			}

			setItems(convert(items));
		} catch (SQLException e) {
			e.printStackTrace();
			setErrorGUI();
		}
	}

}
