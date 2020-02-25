package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.Bank;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BankChooserEvent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BlockLib;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class ChooseBankGUI extends WEGUI {

	public ChooseBankGUI(WEGUI parent, Player player, BankChooserEvent chooserEvent, String title) {
		super(parent, new GUIItem[] {}, title);

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		items.add(new GUIItem(0, 4, mkItem(Material.OAK_SIGN, title)) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});

		try {
			List<Bank> banks = WEDB.getAllBanks();
			for (Bank bank : banks) {
				items.add(new GUIItem(slot, mkItem(BlockLib.BANK, bank.name)) {
					@Override
					public void event(InventoryClickEvent event) {
						player.closeInventory();
						chooserEvent.event(event, bank);
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

	public ChooseBankGUI(Player player, BankChooserEvent chooserEvent, String title) {
		super(new GUIItem[] {}, title);

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		items.add(new GUIItem(0, 4, mkItem(Material.OAK_SIGN, title)) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});

		try {
			List<Bank> banks = WEDB.getAllBanks();
			for (Bank bank : banks) {
				items.add(new GUIItem(slot, mkItem(BlockLib.BANK, bank.name)) {
					@Override
					public void event(InventoryClickEvent event) {
						player.closeInventory();
						chooserEvent.event(event, bank);
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
