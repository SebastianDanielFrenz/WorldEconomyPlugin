package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WEGUI implements InventoryHolder {
	// Create a new inventory, with "this" owner for comparison with other
	// inventories, a size of nine, called example
	public final Inventory inv;
	private GUIItem[] items;

	private int page;
	private boolean fits_on_one_screen = true;

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
		initializeItems(items);

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

		initializeItems(items2);

		WEGUIRegistry.GUIs.add(this);
	}

	public WEGUI(GUIItem[] items, String title) {
		inv = Bukkit.createInventory(this, 54, title);
		initializeItems(items);

		WEGUIRegistry.GUIs.add(this);
	}

	@Override
	public Inventory getInventory() {
		return inv;
	}

	// You can call this whenever you want to put the items in
	public void initializeItems(GUIItem[] items) {
		this.items = items;

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
				if (item.slot > page * 9 * 4 && item.slot < (page + 1) * 9 * 4) {
					inv.setItem(item.slot - page * 9 * 4 + getOffset(), item.itemStack);
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
				inv.setItem(5 * 9 + 4, WEGUIs.mkItem(Material.GOLD_NUGGET, "Page " + (page)));
				items2[items.length + ext] = new GUIItem(-5, null) {
					@Override
					public void event(InventoryClickEvent event) {
						page--;
						initializeItems(items);
					}
				};
				ext++;
			}
			if (page != highest) {
				inv.setItem(5 * 9 + 6, WEGUIs.mkItem(Material.GOLD_NUGGET, "Page " + (page + 2)));
				items2[items.length + ext] = new GUIItem(-3, null) {
					@Override
					public void event(InventoryClickEvent event) {
						event.getWhoClicked().sendMessage("hi!");
						page++;
						initializeItems(items);
					}
				};
				ext++;
			}

			this.items = items2;

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
		System.out.println("Raw: " + e.getRawSlot());

		if (fits_on_one_screen) {
			for (int i = 0; i < items.length; i++) {
				if (items[i].slot == e.getRawSlot()) {
					items[i].event(e);
					return;
				}
			}
		} else {
			for (int i = 0; i < items.length; i++) {
				if (e.getRawSlot() < 9) {
					if (items[i].slot == e.getRawSlot()) {
						items[i].event(e);
					}
				} else if (e.getRawSlot() >= 5 * 9) {
					if (items[i].slot == e.getRawSlot() - 6 * 9) {
						items[i].event(e);
						System.out.println("click!");
					}
				} else {
					if (items[i].slot == e.getRawSlot() + page * 9 * 4) {
						items[i].event(e);
					}

				}
			}
		}
	}
}
