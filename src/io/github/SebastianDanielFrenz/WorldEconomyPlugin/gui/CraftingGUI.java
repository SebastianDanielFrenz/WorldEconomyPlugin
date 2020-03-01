package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineInventory;

public class CraftingGUI extends WEGUI {

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
					WEGUIRegistry.GUIs.add(parent);
				}
			};
		} else {
			this.items = items;
		}
	}

	public CraftingGUI parent;

	public CraftingGUI(GUIItem[] items, MachineInventory inv) {
		super(items);
	}

	public CraftingGUI(CraftingGUI parent, GUIItem[] items, String title, MachineInventory inv) {
		super(parent, items, title);
	}

	public CraftingGUI(GUIItem[] items, String title, MachineInventory inv) {
		super(items, title);
	}

	@Override
	public void initializeItems(GUIItem[] items) {
		
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
		System.out.println("Raw: " + e.getRawSlot());

		if (fits_on_one_screen) {
			for (int i = 0; i < items.length; i++) {
				if (items[i].slot == e.getRawSlot()) {
					items[i].event(e);
					return;
				}
			}
		} else {

			if (e.getRawSlot() < 9) {
				System.out.println("top slot " + e.getRawSlot());
			} else if (e.getRawSlot() >= 5 * 9) {
				System.out.println("bottom slot " + (e.getRawSlot() - (9 * 6)));
			} else {
				System.out.println("middle slot " + (e.getRawSlot() + (page * 9 * 4)));
			}

			for (int i = 0; i < items.length; i++) {
				if (e.getRawSlot() < 9) {
					if (items[i].slot == e.getRawSlot()) {
						items[i].event(e);
						break;
					}
				} else if (e.getRawSlot() >= 5 * 9) {
					if (items[i].slot == e.getRawSlot() - 6 * 9) {
						items[i].event(e);
						break;
					}
				} else {
					if (items[i].slot == e.getRawSlot() + page * 9 * 4) {
						items[i].event(e);
						break;
					}

				}
			}
		}
	}

	public void setErrorGUI() {
		GUIItem[] items = new GUIItem[9 * 4];
		for (int i = 0; i < items.length; i++) {
			items[i] = new GUIItem(i + 9, mkItem(Material.BARRIER, "§4§lERROR")) {
				@Override
				public void event(InventoryClickEvent event) {
				}
			};
		}
		setItems(items);
	}

	public static GUIItem[] convert(List<GUIItem> list) {
		GUIItem[] out = new GUIItem[list.size()];
		for (int i = 0; i < list.size(); i++) {
			out[i] = list.get(i);
		}
		return out;
	}

	public static ItemStack mkItem(Material material, String name, String[] lore) {
		ItemStack out = new ItemStack(material);
		ItemMeta meta = out.getItemMeta();
		ArrayList<String> _lore = new ArrayList<String>();

		for (int i = 0; i < lore.length; i++) {
			_lore.add(lore[i]);
		}

		meta.setLore(_lore);
		meta.setDisplayName(name);
		out.setItemMeta(meta);
		return out;
	}

	public static ItemStack mkItem(Material material, String name) {
		ItemStack out = new ItemStack(material);
		ItemMeta meta = out.getItemMeta();

		meta.setDisplayName(name);
		out.setItemMeta(meta);
		return out;
	}

	public static ItemStack mkItem(Material material, int amount, String name, String[] lore) {
		ItemStack out = new ItemStack(material, amount);
		ItemMeta meta = out.getItemMeta();
		ArrayList<String> _lore = new ArrayList<String>();

		for (int i = 0; i < lore.length; i++) {
			_lore.add(lore[i]);
		}

		meta.setLore(_lore);
		meta.setDisplayName(name);
		out.setItemMeta(meta);
		return out;
	}

	public static ItemStack mkItem(Material material, int amount, String name) {
		ItemStack out = new ItemStack(material, amount);
		ItemMeta meta = out.getItemMeta();

		meta.setDisplayName(name);
		out.setItemMeta(meta);
		return out;
	}
}
