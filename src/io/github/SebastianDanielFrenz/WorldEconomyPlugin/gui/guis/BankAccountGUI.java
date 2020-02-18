package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class BankAccountGUI extends WEGUI {

	public BankAccountGUI(WEGUI parent, BankAccount account) {
		super(parent, new GUIItem[] {}, "Bank Account - " + account.getName());

		BankAccountGUI out = this;

		setItems(new GUIItem[] { new GUIItem(0, 4, mkItem(Material.OAK_SIGN, "Bank Account - " + account.getName())) {
			@Override
			public void event(InventoryClickEvent event) {

			}
		}, new GUIItem(1, 0, mkItem(Material.PAPER, "Credits")) {
			@Override
			public void event(InventoryClickEvent event) {
				new BankAccountCreditsGUI(out, account).openInventory((Player) event.getWhoClicked());
			}
		} });
	}

}
