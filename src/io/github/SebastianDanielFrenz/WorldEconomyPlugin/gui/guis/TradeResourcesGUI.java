package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.ResourceChooserEvent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class TradeResourcesGUI extends ChooseResourceGUI {

	public TradeResourcesGUI() {
		super(new ResourceChooserEvent() {
			@Override
			public void event(InventoryClickEvent event, Material material) {
				new ResourceGUI((WEGUI) event.getClickedInventory().getHolder(), material);
			}
		});
	}

}
