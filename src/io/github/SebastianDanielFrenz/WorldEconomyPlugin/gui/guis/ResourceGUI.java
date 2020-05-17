package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BlockLib;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class ResourceGUI extends WEGUI {

	public ResourceGUI(WEGUI parent, CustomItem resource) {
		super(new GUIItem[] { new GUIItem(0, 4, mkItem(resource.base_material, 1, resource.vanilla_data,
				"Resource - " + resource.item_name + " - " + WEDB.getResourcePriceWithFallback(resource))) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		}, new GUIItem(1, 0, mkItem(BlockLib.BUY, "Buy")) {
			@Override
			public void event(InventoryClickEvent event) {
				new BuyResourceGUI((WEGUI) event.getClickedInventory().getHolder(), (Player) event.getWhoClicked(),
						resource).openInventory((Player) event.getWhoClicked());
			}
		}, new GUIItem(1, 1, mkItem(BlockLib.SELL, "Sell")) {
			@Override
			public void event(InventoryClickEvent event) {
				// TODO
			}
		}, }, "Resource - " + resource.item_name);
	}

}
