package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Lang;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class BankAccountGUI extends WEGUI {

	public BankAccountGUI(WEGUI parent, BankAccount account, Player player) {
		super(parent, new GUIItem[] {}, Lang.GUI_TITLE_BANK_ACCOUNT(player, account.getName()), player);

		BankAccountGUI out = this;

		setItems(new GUIItem[] {
				new GUIItem(0, 4, mkItem(Material.SIGN, Lang.GUI_TITLE_BANK_ACCOUNT(player, account.getName()))) {
					@Override
					public void event(InventoryClickEvent event) {

					}
				}, new GUIItem(1, 0, mkItem(Material.PAPER, Lang.getGuiItemBankAccount_Credits(player))) {
					@Override
					public void event(InventoryClickEvent event) {
						new BankAccountCreditsGUI(out, account, player).openInventory();
					}
				} });
	}

}
