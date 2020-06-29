package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Lang;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BankAccountChooserEvent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BlockLib;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class ChooseBankAccountGUI extends WEGUI {

	public ChooseBankAccountGUI(WEGUI parent, String title, long bankingID, BankAccountChooserEvent chooserEvent,
			Player player) {
		super(parent, new GUIItem[] {}, title, player);

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		try {
			List<BankAccount> accounts = WEDB.getBankAccounts(bankingID);
			for (BankAccount account : accounts) {
				items.add(new GUIItem(slot, BlockLib.bank_account(account)) {
					@Override
					public void event(InventoryClickEvent event) {
						event.getWhoClicked().closeInventory();
						chooserEvent.event(event, account);
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

	public ChooseBankAccountGUI(WEGUI parent, long bankingID, BankAccountChooserEvent chooserEvent, Player player) {
		this(parent, Lang.get(player, Lang.GUI_TITLE_CHOOSE_BANK_ACCOUNT), bankingID, chooserEvent, player);
	}

}
