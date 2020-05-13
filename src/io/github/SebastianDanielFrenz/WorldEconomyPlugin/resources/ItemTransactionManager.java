package io.github.SebastianDanielFrenz.WorldEconomyPlugin.resources;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.MissuseWarning;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;

public class ItemTransactionManager {

	public static int getSpace(Inventory inv, Material material, int data) {
		int out = 0;
		for (ItemStack slot : inv.getStorageContents()) {
			if (slot == null) {
				out += material.getMaxStackSize();
			} else if (slot.getType() == material && slot.getData().getData() == data) {
				out += slot.getMaxStackSize() - slot.getAmount();
			}
		}
		return out;
	}

	/**
	 * The item count of the CustomItemStack is irrelevant.
	 * 
	 * @param inv
	 * @param stack
	 * @return
	 */
	public static int getSpace(Inventory inv, CustomItemStack stack) {
		int out = 0;
		for (ItemStack slot : inv.getStorageContents()) {
			if (slot == null) {
				out += stack.getItem().base_material.getMaxStackSize();
			} else if (stack.matches(slot)) {
				out += slot.getMaxStackSize() - slot.getAmount();
			}
		}
		return out;
	}

	public static boolean canFit(Inventory inv, Material material, int amount, int data) {
		return getSpace(inv, material, data) >= amount;
	}

	public static boolean canFit(Inventory inv, CustomItemStack stack) {
		return getSpace(inv, stack) >= stack.getCount();
	}

	@MissuseWarning(text = "Please check for usable inventory space before using this function.")
	public static void give(Inventory inv, Material material, int data, int amount) {
		int done = 0;
		ItemStack[] items = inv.getContents();
		for (int i = 0; i < items.length && amount > done; i++) {
			if (items[i] == null) {
				ItemStack _item = new ItemStack(material);

				if (_item.getMaxStackSize() > amount - done) {
					_item.setAmount(amount - done);
					done += amount - done;
					inv.setItem(i, _item);
					break;
				} else {
					_item.setAmount(_item.getMaxStackSize());
					done += _item.getMaxStackSize();
					inv.setItem(i, _item);
				}
				continue;
			}

			if (items[i].getType() == material && items[i].getData().getData() == data) {
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

			if (stack.matches(items[i])) {

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

				inv.setItem(i, new ItemStack(stack.getItem().base_material, 1, stack.getItem().vanilla_data));
				ItemMeta meta = inv.getItem(i).getItemMeta();
				meta.setDisplayName(stack.getItem().item_name);
				inv.getItem(i).setItemMeta(meta);

				if (inv.getItem(i).getMaxStackSize() > amount - done) {
					inv.getItem(i).setAmount(amount - done);
					done = amount;
					break;
				} else {
					inv.getItem(i).setAmount(inv.getItem(i).getMaxStackSize());
					done += inv.getItem(i).getMaxStackSize();
				}
			}
		}
		if (done != amount) {
			throw new RuntimeException("Not enough space in inventory!");
		}
	}

	public static int getPresent(Inventory inv, Material material, int data) {
		int out = 0;
		for (ItemStack slot : inv.getContents()) {
			if (slot.getType() == material && slot.getData().getData() == data) {
				out += slot.getAmount();
			}
		}
		return out;
	}

	public static boolean canConsume(Inventory inv, Material material, int amount, int data) {
		return getPresent(inv, material, data) >= amount;
	}

	public static int getPresent(Inventory inv, CustomItem item) {
		int out = 0;
		for (ItemStack slot : inv.getContents()) {
			if (slot.getType() == item.base_material && slot.getData().getData() == item.vanilla_data
					&& slot.hasItemMeta() ? slot.getItemMeta().getDisplayName().equals(item.item_name)
							: item.item_name == null) {
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

	public static int getSpace(Inventory inv, Material material, int data, int limit) {
		int out = 0;
		ItemStack slot;
		for (int i = 0; i < limit; i++) {
			slot = inv.getStorageContents()[i];
			if (slot.getType() == material && slot.getData().getData() == data) {
				out += slot.getMaxStackSize() - slot.getAmount();
			}
		}
		return out;
	}

	public static boolean canFit(Inventory inv, Material material, int amount, int data, int limit) {
		return getSpace(inv, material, data, limit) >= amount;
	}

	@MissuseWarning(text = "Please check for usable inventory space before using this function.")
	public static void give(Inventory inv, Material material, int amount, int data, int limit) {
		int done = 0;
		ItemStack[] items = inv.getContents();
		for (int i = 0; i < limit && amount < done; i++) {
			if (items[i].getType() == material && items[i].getData().getData() == data) {
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

			if (stack.matches(items[i])) {

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

				inv.setItem(i, new ItemStack(stack.getItem().base_material, 1, stack.getItem().vanilla_data));
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

	public static int getPresent(Inventory inv, Material material, int data, int limit) {
		int out = 0;
		ItemStack slot;
		for (int i = 0; i < limit; i++) {
			slot = inv.getContents()[i];
			if (slot.getType() == material && slot.getData().getData() == data) {
				out += slot.getAmount();
			}
		}
		return out;
	}

	public static boolean canConsume(Inventory inv, Material material, int amount, int data, int limit) {
		return getPresent(inv, material, data, limit) >= amount;
	}

	public static int getPresent(Inventory inv, CustomItem item, int limit) {
		int out = 0;
		ItemStack slot;
		for (int i = 0; i < limit; i++) {
			slot = inv.getContents()[i];
			if (slot.getType() == item.base_material && slot.getData().getData() == item.vanilla_data
					&& slot.hasItemMeta() ? slot.getItemMeta().getDisplayName().equals(item.item_name)
							: item.item_name == null) {
				out += slot.getAmount();
			}
		}
		return out;
	}

	public static boolean canConsume(Inventory inv, CustomItem item, int amount, int limit) {
		return getPresent(inv, item, limit) >= amount;
	}

}
