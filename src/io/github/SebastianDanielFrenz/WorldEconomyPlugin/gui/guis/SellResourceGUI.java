package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BankAccountChooserEvent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BlockLib;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class SellResourceGUI extends WEGUI {

	public SellResourceGUI(WEGUI parent, Player player, CustomItem resource) {
		super(new GUIItem[] {}, "Sell Resource - " + resource.name());

		List<GUIItem> items = new ArrayList<GUIItem>();

		items.add(new GUIItem(0, 4, mkItem(resource.base_material,
				"Sell Resource - " + resource.name() + " - " + WEDB.getResourcePriceWithFallback(resource))) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});
		items.add(new GUIItem(1, 0, mkItem(Material.OAK_SIGN, "Enter a custom amount")) {
			@Override
			public void event(InventoryClickEvent event) {
				// TODO
			}
		});
		for (long i = 1; i < 100000; i *= 8) {
			long x = i;
			items.add(new GUIItem(1, 1, mkItem(BlockLib.SELL, String.valueOf(i))) {
				@Override
				public void event(InventoryClickEvent event) {
					try {
						new ChooseBankAccountGUI(parent, WEDB.getUserProfile(player).bankingID,
								new BankAccountChooserEvent() {
									@Override
									public void event(InventoryClickEvent event, BankAccount account) {
										try {
											// code
											throw new SQLException();

										} catch (SQLException e) {
											e.printStackTrace();
											player.sendMessage(
													WorldEconomyPlugin.PREFIX + "§4An internal error occured!");
										}
									}
								}).openInventory(player);

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
		}

		setItems(convert(items));
	}

}
