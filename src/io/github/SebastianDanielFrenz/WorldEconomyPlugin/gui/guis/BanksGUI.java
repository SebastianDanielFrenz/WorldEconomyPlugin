package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Lang;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.Bank;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BlockLib;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class BanksGUI extends WEGUI {

	public BanksGUI(Player player) {
		super(new GUIItem[] {}, Lang.getGuiTitleBanks(player), player);

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		items.add(new GUIItem(0, 4, mkItem(Material.SIGN, Lang.getGuiTitleBanks(player))) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});
		try {
			List<Bank> banks = WEDB.getAllBanks();
			for (Bank bank : banks) {
				items.add(new GUIItem(slot, BlockLib.bank(bank)) {
					@Override
					public void event(InventoryClickEvent event) {
					}
				});

				slot++;

				setItems(convert(items));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			setErrorGUI();
		}
	}

	public BanksGUI(WEGUI parent, Player player) {
		super(parent, new GUIItem[] {}, Lang.getGuiTitleBanks(player), player);

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		items.add(new GUIItem(0, 4, mkItem(Material.SIGN, Lang.getGuiTitleBanks(player))) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});

		try {
			List<Bank> banks = WEDB.getAllBanks();
			for (Bank bank : banks) {
				items.add(new GUIItem(slot, BlockLib.bank(bank)) {
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
