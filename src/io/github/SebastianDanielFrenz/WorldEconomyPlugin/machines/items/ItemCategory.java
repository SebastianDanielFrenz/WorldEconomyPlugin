package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.Machine;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.recipes.MachineRecipe;

public enum ItemCategory {

	RAW_MATERIALS(CustomItem.IRON_INGOT.base_material),
	PROCESSED_MATERIALS(CustomItem.IRON_PLATE.base_material),
	MACHINES(CustomItem.BASIC_FURNACE_STAGE1.base_material),
	EQUIPMENT(CustomItem.IRON_CHESTPLATE.base_material),
	TECHNOLOGY(CustomItem.REDSTONE.base_material),
	OTHER(CustomItem.CLAY_BUCKET.base_material);

	private ItemCategory(Material display) {
		this.display = display;
	}

	public String getTitle() {
		return name().replace('_', ' ').toLowerCase();
	}

	public final Material display;

	public static List<MachineRecipe> getRecipes(Machine machine, ItemCategory category) {
		List<MachineRecipe> out = new ArrayList<MachineRecipe>();
		boolean found;
		for (MachineRecipe recipe : machine.getRecipes()) {
			found = false;
			for (CustomItemStack outputStack : recipe.getOutput()) {
				if (outputStack.getItem().category == category) {
					found = true;
					break;
				}
			}
			if (found) {
				out.add(recipe);
			}
		}
		return out;
	}

}
