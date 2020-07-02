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
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BankAccountChooserEvent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BlockLib;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class SellResourceGUI extends WEGUI {

	public SellResourceGUI(WEGUI parent, Player player, CustomItem resource) {
		super(new GUIItem[] {}, Lang.GUI_TITLE_SELL_RESOURCE(player, resource), player);

		List<GUIItem> items = new ArrayList<GUIItem>();

		items.add(new GUIItem(0, 4, mkItem(resource.base_material,
				Lang.GUI_TITLE_SELL_RESOURCE(player, resource) + " - " + WEDB.getResourcePriceWithFallback(resource))) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});
		items.add(
				new GUIItem(1, 0, mkItem(Material.SIGN, Lang.get(player, Lang.GUI_ITEM_SELL_RESOURCE__CUSTOM_AMOUNT))) {
					@Override
					public void event(InventoryClickEvent event) {
						// TODO
					}
				});
		for (long i = 1; i < 100000; i *= 8) {
			// long x = i;
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
											player.sendMessage(Lang.getError(player, Lang.ERROR_INTERNAL));
										}
									}
								}, player).openInventory();

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
		}

		setItems(convert(items));
	}

}
