package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class ResourceChooserEvent {

	public abstract void event(InventoryClickEvent event, Material material);

}
