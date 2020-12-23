package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Lang;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BlockLib;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class BankAccountsGUI extends WEGUI {

	public BankAccountsGUI(WEGUI parent, Player player) {
		super(parent, new GUIItem[] {}, Lang.getGuiTitleBankAccounts(player), player);

		BankAccountsGUI out = this;

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		items.add(new GUIItem(0, 4, mkItem(Material.SIGN, Lang.getGuiTitleBankAccounts(player))) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});

		try {
			List<BankAccount> bank_accounts = WEDB.getAllBankAccounts(player);
			for (BankAccount bank_account : bank_accounts) {
				items.add(new GUIItem(slot, BlockLib.bank_account(bank_account)) {
					@Override
					public void event(InventoryClickEvent event) {
						new BankAccountGUI(out, bank_account, player).openInventory();
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
