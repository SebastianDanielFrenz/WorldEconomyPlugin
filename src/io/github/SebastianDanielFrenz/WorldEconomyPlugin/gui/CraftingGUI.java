package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Utils;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.Machine;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineInventory;

public class CraftingGUI extends WEGUI {

	protected int[] inv_representing_slots;
	protected MachineInventory storage_inv;

	public CraftingGUI(GUIItem[] items, MachineInventory inv, int[] inv_representing_slots) {
		super(items);
		this.inv_representing_slots = inv_representing_slots;
		storage_inv = inv;
	}

	public CraftingGUI(CraftingGUI parent, GUIItem[] items, String title, MachineInventory inv,
			int[] inv_representing_slots) {
		super(parent, items, title);
		this.inv_representing_slots = inv_representing_slots;
		storage_inv = inv;
	}

	public CraftingGUI(GUIItem[] items, String title, MachineInventory inv, int[] inv_representing_slots) {
		super(items, title);
		this.inv_representing_slots = inv_representing_slots;
		storage_inv = inv;
	}

	public CraftingGUI(GUIItem[] items, String title, Machine machine, int[] inv_representing_slots) {
		super(items, title);
		// this.inv_representing_slots = inv_representing_slots;
		this.inv_representing_slots = new int[] { 9 + 4 };
		storage_inv = new MachineInventory(machine.getInventory(), inv_representing_slots.length);
	}

	@Override
	public void initializeItems(GUIItem[] items) {
		System.out.println(inv_representing_slots);
		GUIItem[] new_items = new GUIItem[items.length + inv_representing_slots.length];
		for (int i = 0; i < items.length; i++) {
			new_items[i] = items[i];
		}
		for (int i = 0; i < inv_representing_slots.length; i++) {
			new_items[items.length + i] = new GUIItem(inv_representing_slots[i], storage_inv.inv.getContents()[i]) {
				@Override
				public void event(InventoryClickEvent event) {
				}
			};
		}

		super.initializeItems(new_items);
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
						if (Utils.in(inv_representing_slots, e.getRawSlot())) {
							e.setCancelled(false);
							storage_inv.inv.setItem(Utils.indexOf(inv_representing_slots, e.getRawSlot()),
									e.getCurrentItem());
						}
						break;
					}
				} else if (e.getRawSlot() >= 5 * 9) {
					if (items[i].slot == e.getRawSlot() - 6 * 9) {
						items[i].event(e);
						if (Utils.in(inv_representing_slots, e.getRawSlot() - 6 * 9)) {
							e.setCancelled(false);
							storage_inv.inv.setItem(Utils.indexOf(inv_representing_slots, e.getRawSlot() - 6 * 9),
									e.getCurrentItem());
						}
						break;
					}
				} else {
					if (items[i].slot == e.getRawSlot() + page * 9 * 4) {
						items[i].event(e);
						if (Utils.in(inv_representing_slots, e.getRawSlot() + page * 9 * 4)) {
							e.setCancelled(false);
							storage_inv.inv.setItem(
									Utils.indexOf(inv_representing_slots, e.getRawSlot() + page * 9 * 4),
									e.getCurrentItem());
						}
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
}
