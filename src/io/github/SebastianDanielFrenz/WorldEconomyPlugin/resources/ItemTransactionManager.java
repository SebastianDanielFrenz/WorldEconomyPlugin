package io.github.SebastianDanielFrenz.WorldEconomyPlugin.resources;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.MissuseWarning;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItemStack;

public class ItemTransactionManager {

	public static int getSpace(Inventory inv, Material material) {
		int out = 0;
		for (ItemStack slot : inv.getStorageContents()) {
			if (slot.getType() == material) {
				out += slot.getMaxStackSize() - slot.getAmount();
			}
		}
		return out;
	}

	public static boolean canFit(Inventory inv, Material material, int amount) {
		return getSpace(inv, material) >= amount;
	}

	@MissuseWarning(text = "Please check for usable inventory space before using this function.")
	public static void give(Inventory inv, Material material, int amount) {
		int done = 0;
		ItemStack[] items = inv.getContents();
		for (int i = 0; i < items.length && amount < done; i++) {
			if (items[i].getType() == material) {
				int space = items[i].getMaxStackSize() - items[i].getAmount();
				if (space > amount - done) {
					items[i].setAmount(items[i].getAmount() + amount - done);
					done += amount - done;
					break;
				} else {
					items[i].setAmount(items[i].getMaxStackSize());
					done += space;
				}
			}
		}
		if (done != amount) {
			throw new RuntimeException("Not enough space in inventory!");
		}
	}

	@MissuseWarning(text = "Please check for usable inventory space before using this function.")
	public static void give(Inventory inv, CustomItemStack stack) {
		int done = 0;
		ItemStack[] items = inv.getContents();
		int amount = stack.getCount();

		for (int i = 0; i < items.length && amount > done; i++) {
			System.out.println(i);

			if (stack.getItem().matches(items[i])) {

				System.out.println("using slot " + i);

				int space = items[i].getMaxStackSize() - items[i].getAmount();

				if (space > amount - done) {
					inv.getItem(i).setAmount(items[i].getAmount() + amount - done);
					done = amount;
					break;
				} else {
					items[i].setAmount(items[i].getMaxStackSize());
					done += space;
				}
			} else if (items[i] == null) {
				System.out.println("using empty slot " + i);

				inv.setItem(i, new ItemStack(stack.getItem().base_material));
				ItemMeta meta = inv.getItem(i).getItemMeta();
				meta.setDisplayName(stack.getItem().item_name);
				inv.getItem(i).setItemMeta(meta);

				if (inv.getItem(i).getMaxStackSize() > amount - done) {
					inv.getItem(i).setAmount(amount - done);
					done = amount;
					break;
				} else {
					inv.getItem(i).setAmount(items[i].getMaxStackSize());
					done += inv.getItem(i).getMaxStackSize();
				}
			}
		}
		if (done != amount) {
			throw new RuntimeException("Not enough space in inventory!");
		}
	}

	public static int getPresent(Inventory inv, Material material) {
		int out = 0;
		for (ItemStack slot : inv.getContents()) {
			if (slot.getType() == material) {
				out += slot.getAmount();
			}
		}
		return out;
	}

	public static boolean canConsume(Inventory inv, Material material, int amount) {
		return getPresent(inv, material) >= amount;
	}

	public static int getPresent(Inventory inv, CustomItem item) {
		int out = 0;
		for (ItemStack slot : inv.getContents()) {
			if (slot.getType() == item.base_material && slot.hasItemMeta()
					? slot.getItemMeta().getDisplayName().equals(item.item_name) : item.item_name == null) {
				out += slot.getAmount();
			}
		}
		return out;
	}

	public static boolean canConsume(Inventory inv, CustomItem item, int amount) {
		return getPresent(inv, item) >= amount;
	}

	/*
	 * ==================================================
	 * 
	 * This section should always be the same as the part above, except it
	 * covers artificially slot limited inventories like machine inventories.
	 * 
	 * ==================================================
	 */

	public static int getSpace(Inventory inv, Material material, int limit) {
		int out = 0;
		ItemStack slot;
		for (int i = 0; i < limit; i++) {
			slot = inv.getStorageContents()[i];
			if (slot.getType() == material) {
				out += slot.getMaxStackSize() - slot.getAmount();
			}
		}
		return out;
	}

	public static boolean canFit(Inventory inv, Material material, int amount, int limit) {
		return getSpace(inv, material, limit) >= amount;
	}

	@MissuseWarning(text = "Please check for usable inventory space before using this function.")
	public static void give(Inventory inv, Material material, int amount, int limit) {
		int done = 0;
		ItemStack[] items = inv.getContents();
		for (int i = 0; i < limit && amount < done; i++) {
			if (items[i].getType() == material) {
				int space = items[i].getMaxStackSize() - items[i].getAmount();
				if (space > amount - done) {
					items[i].setAmount(items[i].getAmount() + amount - done);
					done += amount - done;
					break;
				} else {
					items[i].setAmount(items[i].getMaxStackSize());
					done += space;
				}
			}
		}
		if (done != amount) {
			throw new RuntimeException("Not enough space in inventory!");
		}
	}

	@MissuseWarning(text = "Please check for usable inventory space before using this function.")
	public static void give(Inventory inv, CustomItemStack stack, int limit) {
		int done = 0;
		ItemStack[] items = inv.getContents();
		int amount = stack.getCount();

		for (int i = 0; i < limit && amount > done; i++) {
			System.out.println(i);

			if (stack.getItem().matches(items[i])) {

				System.out.println("using slot " + i);

				int space = items[i].getMaxStackSize() - items[i].getAmount();

				if (space > amount - done) {
					inv.getItem(i).setAmount(items[i].getAmount() + amount - done);
					done = amount;
					break;
				} else {
					items[i].setAmount(items[i].getMaxStackSize());
					done += space;
				}
			} else if (items[i] == null) {
				System.out.println("using empty slot " + i);

				inv.setItem(i, new ItemStack(stack.getItem().base_material));
				ItemMeta meta = inv.getItem(i).getItemMeta();
				meta.setDisplayName(stack.getItem().item_name);
				inv.getItem(i).setItemMeta(meta);

				if (inv.getItem(i).getMaxStackSize() > amount - done) {
					inv.getItem(i).setAmount(amount - done);
					done = amount;
					break;
				} else {
					inv.getItem(i).setAmount(items[i].getMaxStackSize());
					done += inv.getItem(i).getMaxStackSize();
				}
			}
		}
		if (done != amount) {
			throw new RuntimeException("Not enough space in inventory!");
		}
	}

	public static int getPresent(Inventory inv, Material material, int limit) {
		int out = 0;
		ItemStack slot;
		for (int i = 0; i < limit; i++) {
			slot = inv.getContents()[i];
			if (slot.getType() == material) {
				out += slot.getAmount();
			}
		}
		return out;
	}

	public static boolean canConsume(Inventory inv, Material material, int amount, int limit) {
		return getPresent(inv, material, limit) >= amount;
	}

	public static int getPresent(Inventory inv, CustomItem item, int limit) {
		int out = 0;
		ItemStack slot;
		for (int i = 0; i < limit; i++) {
			slot = inv.getContents()[i];
			if (slot.getType() == item.base_material && slot.hasItemMeta()
					? slot.getItemMeta().getDisplayName().equals(item.item_name) : item.item_name == null) {
				out += slot.getAmount();
			}
		}
		return out;
	}

	public static boolean canConsume(Inventory inv, CustomItem item, int amount, int limit) {
		return getPresent(inv, item, limit) >= amount;
	}

}
