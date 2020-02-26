package io.github.SebastianDanielFrenz.WorldEconomyPlugin.resources;

import javax.management.RuntimeErrorException;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.MissuseWarning;

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
			throw new RuntimeException();
		}
	}

}
