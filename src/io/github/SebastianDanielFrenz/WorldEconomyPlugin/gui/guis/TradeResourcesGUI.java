package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.items.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.ResourceChooserEvent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class TradeResourcesGUI extends ChooseItemGUI {

	public TradeResourcesGUI() {
		super(new ResourceChooserEvent() {
			@Override
			public void event(InventoryClickEvent event, CustomItem item) {
				new ResourceGUI((WEGUI) event.getClickedInventory().getHolder(), item)
						.openInventory((Player) event.getWhoClicked());
			}
		});
	}

}
