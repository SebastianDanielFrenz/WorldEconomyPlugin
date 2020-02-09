package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

public class WEGUI implements InventoryHolder {
	// Create a new inventory, with "this" owner for comparison with other
	// inventories, a size of nine, called example
	public final Inventory inv;
	private GUIItem[] items;

	public GUIItem[] getItems() {
		return items;
	}

	public void setItems(GUIItem[] items) {
		inv.clear();

		if (parent != null) {
			GUIItem[] items2 = new GUIItem[items.length + 1];
			for (int i = 0; i < items.length; i++) {
				items2[i] = items[i];
			}

			// other constructor equivalent
			this.items = items2;

			ItemStack backButtonItem = new ItemStack(Material.RED_WOOL);
			ItemMeta meta = backButtonItem.getItemMeta();
			meta.setDisplayName("§4Back");
			backButtonItem.setItemMeta(meta);

			items2[items.length] = new GUIItem(0, 8, backButtonItem) {
				@Override
				public void event(InventoryClickEvent event) {
					parent.openInventory((Player) event.getWhoClicked());
					WorldEconomyPlugin.guiRegister.GUIs.add(parent);
				}
			};
		} else {
			this.items = items;
		}
	}

	public WEGUI parent;

	public WEGUI(GUIItem[] items) {
		inv = Bukkit.createInventory(this, 54);
		initializeItems(items);

		WorldEconomyPlugin.guiRegister.GUIs.add(this);
	}

	public WEGUI(WEGUI parent, GUIItem[] items, String title) {
		inv = Bukkit.createInventory(this, 54, title);

		GUIItem[] items2 = new GUIItem[items.length + 1];
		for (int i = 0; i < items.length; i++) {
			items2[i] = items[i];
		}

		// other constructor equivalent
		this.parent = parent;
		this.items = items2;

		ItemStack backButtonItem = new ItemStack(Material.RED_WOOL);
		ItemMeta meta = backButtonItem.getItemMeta();
		meta.setDisplayName("§4Back");
		backButtonItem.setItemMeta(meta);

		items2[items.length] = new GUIItem(0, 8, backButtonItem) {
			@Override
			public void event(InventoryClickEvent event) {
				parent.openInventory((Player) event.getWhoClicked());
				WorldEconomyPlugin.guiRegister.GUIs.add(parent);
			}
		};

		initializeItems(items2);

		WorldEconomyPlugin.guiRegister.GUIs.add(this);
	}

	public WEGUI(GUIItem[] items, String title) {
		inv = Bukkit.createInventory(this, 54, title);
		initializeItems(items);

		WorldEconomyPlugin.guiRegister.GUIs.add(this);
	}

	@Override
	public Inventory getInventory() {
		return inv;
	}

	// You can call this whenever you want to put the items in
	public void initializeItems(GUIItem[] items) {
		this.items = items;

		for (int i = 0; i < items.length; i++) {
			inv.setItem(items[i].slot, items[i].itemStack);
		}
	}

	// You can open the inventory with this
	public void openInventory(Player player) {
		initializeItems(items);
		player.openInventory(inv);
	}

	// Check for clicks on items
	public void eventHandler(InventoryClickEvent e) {

		e.setCancelled(true);

		ItemStack clickedItem = e.getCurrentItem();

		// verify current item is not null
		if (clickedItem == null || clickedItem.getType() == Material.AIR)
			return;

		// Using slots click is a best option for your inventory click's
		for (int i = 0; i < items.length; i++) {
			if (items[i].slot == e.getRawSlot()) {
				items[i].event(e);
				return;
			}
		}
	}
}
