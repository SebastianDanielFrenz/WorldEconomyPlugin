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
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BankAccountChooserEvent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BlockLib;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.resources.ItemTransactionManager;

public class BuyResourceGUI extends WEGUI {

	public BuyResourceGUI(WEGUI parent, Player player, CustomItem resource) {
		super(new GUIItem[] {}, "Buy Resource - " + resource.item_name);

		List<GUIItem> items = new ArrayList<GUIItem>();

		items.add(new GUIItem(0, 4, mkItem(resource.base_material,
				"Buy Resource - " + resource.item_name + " - " + WEDB.getResourcePriceWithFallback(resource))) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});
		items.add(new GUIItem(1, 0, mkItem(Material.SIGN, "Enter a custom amount")) {
			@Override
			public void event(InventoryClickEvent event) {
				// TODO
			}
		});
		for (long i = 1; i < 100000; i *= 8) {
			long x = i;
			items.add(new GUIItem(1, 1, mkItem(BlockLib.BUY, String.valueOf(i))) {
				@Override
				public void event(InventoryClickEvent event) {
					try {
						new ChooseBankAccountGUI(parent, WEDB.getUserProfile(player).bankingID,
								new BankAccountChooserEvent() {
									@Override
									public void event(InventoryClickEvent event, BankAccount account) {
										try {
											double price = WEDB.getResourcePriceBuy(resource, x);
											if (account.getBalance() < price) {
												player.sendMessage(WorldEconomyPlugin.PREFIX
														+ "§4You do not have enough money to buy " + x + "x "
														+ resource.item_name + " (requires " + price + ")");
											} else {
												if (ItemTransactionManager.canFit(player.getInventory(),
														new CustomItemStack(resource, (int) x))) {
													WEDB.setBankAccountBalance(account, account.getBalance() - price);
													ItemTransactionManager.give(player.getInventory(),
															new CustomItemStack(resource, (int) x));

												} else {
													player.sendMessage(WorldEconomyPlugin.PREFIX
															+ "§4You only have space for "
															+ ItemTransactionManager.getSpace(player.getInventory(),
																	new CustomItemStack(resource, (int) x))
															+ " items!");
													player.closeInventory();
												}
											}
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
