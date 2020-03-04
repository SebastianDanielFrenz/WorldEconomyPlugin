package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WEGUI implements InventoryHolder {
	// Create a new inventory, with "this" owner for comparison with other
	// inventories, a size of nine, called example
	public final Inventory inv;
	protected GUIItem[] items;

	protected int page;
	protected boolean fits_on_one_screen = true;

	public int getOffset() {
		return page == 0 ? 0 : 9;
	}

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
					WEGUIRegistry.GUIs.add(parent);
				}
			};
		} else {
			this.items = items;
		}
	}

	public WEGUI parent;

	public WEGUI(GUIItem[] items) {
		inv = Bukkit.createInventory(this, 54);

		WEGUIRegistry.GUIs.add(this);
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
				WEGUIRegistry.GUIs.add(parent);
			}
		};

		WEGUIRegistry.GUIs.add(this);
	}

	public WEGUI(GUIItem[] items, String title) {
		inv = Bukkit.createInventory(this, 54, title);

		this.items = items;

		WEGUIRegistry.GUIs.add(this);
	}

	@Override
	public Inventory getInventory() {
		return inv;
	}

	// You can call this whenever you want to put the items in
	public GUIItem[] initializeItems(GUIItem[] items) {

		// check whether all the items fit on one screen
		fits_on_one_screen = true;
		for (GUIItem item : items) {
			if (item.slot > 53) {
				fits_on_one_screen = false;
				break;
			}
		}

		if (fits_on_one_screen) {
			for (int i = 0; i < items.length; i++) {
				inv.setItem(items[i].slot, items[i].itemStack);
			}
			return items;
		} else {
			// if previously the items did not fit on screen, the space needs to
			// be cleared! (the GUI updates without reopening)
			for (int i = 0; i < 9 * 6; i++) {
				inv.setItem(i, null);
			}
			// get the page count
			int highest = 0;
			int c;

			for (GUIItem item : items) {
				c = item.slot / (4 * 9);
				if (c > highest) {
					highest = c;
				}
			}

			for (GUIItem item : items) {
				if (page == 0) {
					if (item.slot >= 9 && item.slot < 45) {
						inv.setItem(item.slot, item.itemStack);
					}
				} else {
					if (item.slot >= page * 9 * 4 && item.slot < (page + 1) * 9 * 4) {
						inv.setItem(item.slot - page * 9 * 4 + getOffset(), item.itemStack);
					}
				}
			}

			for (GUIItem item : items) {
				if (item.slot < 9 && item.slot >= 0) {
					inv.setItem(item.slot, item.itemStack);
				}
			}

			int ext = 0;
			if (page != 0) {
				ext++;
			}
			if (page != highest) {
				ext++;
			}

			GUIItem[] items2 = new GUIItem[items.length + ext];
			for (int i = 0; i < items.length; i++) {
				items2[i] = items[i];
			}
			// bottom menu items
			ext = 0;
			if (page != 0) {
				inv.setItem(5 * 9 + 3, mkItem(Material.GOLD_NUGGET, "Page " + (page)));
				items2[items.length + ext] = new GUIItem(-6, null) {
					@Override
					public void event(InventoryClickEvent event) {
						page--;
						System.out.println("page back");
						initializeItems(items);
					}
				};
				ext++;
			}

			if (page != highest) {
				inv.setItem(5 * 9 + 5, mkItem(Material.GOLD_NUGGET, "Page " + (page + 2)));
				items2[items.length + ext] = new GUIItem(-4, null) {
					@Override
					public void event(InventoryClickEvent event) {
						page++;
						System.out.println("page foward");
						initializeItems(items);
					}
				};
				ext++;
			}

			return items2;
		}
	}

	// You can open the inventory with this
	public void openInventory(Player player) {
		items = initializeItems(items);
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

	public void eventHandler(InventoryDragEvent event) {
		event.setCancelled(true);
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
