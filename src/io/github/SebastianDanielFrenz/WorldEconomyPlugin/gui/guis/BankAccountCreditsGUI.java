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
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.credit.Credit;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class BankAccountCreditsGUI extends WEGUI {

	public BankAccountCreditsGUI(WEGUI parent, BankAccount account, Player player) {
		super(parent, new GUIItem[] {}, Lang.getGuiTitleBankAccountCredits(player), player);

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		try {
			List<Credit> credits = WEDB.getBankAccountCredits(account.getID());
			for (Credit credit : credits) {
				items.add(
						new GUIItem(slot, mkItem(Material.PAPER, String.valueOf(credit.amount), new String[] { WEDB.getBank(credit.bankID).name })) {
							@Override
							public void event(InventoryClickEvent event) {
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
