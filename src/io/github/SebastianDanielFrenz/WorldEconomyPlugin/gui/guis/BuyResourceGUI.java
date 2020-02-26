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
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BankAccountChooserEvent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BlockLib;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.resources.ItemTransactionManager;

public class BuyResourceGUI extends WEGUI {

	public BuyResourceGUI(WEGUI parent, Player player, Material resource) {
		super(new GUIItem[] {}, "Buy Resource - " + resource.name());

		List<GUIItem> items = new ArrayList<GUIItem>();

		items.add(new GUIItem(0, 4, mkItem(resource,
				"Buy Resource - " + resource.name() + " - " + WEDB.getResourcePriceWithFallback(resource))) {
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
														+ resource.name() + " (requires " + price + ")");
											} else {
												if (ItemTransactionManager.canFit(player.getInventory(), resource,
														(int) x)) {
													WEDB.setBankAccountBalance(account, account.getBalance() - price);
													// TODO give items
													
												} else {
													player.sendMessage(WorldEconomyPlugin.PREFIX
															+ "§4You only have space for " + ItemTransactionManager
																	.getSpace(player.getInventory(), resource)
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
