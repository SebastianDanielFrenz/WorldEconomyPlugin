package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;

public abstract class ResourceChooserEvent {

	public abstract void event(InventoryClickEvent event, CustomItem item);

}
