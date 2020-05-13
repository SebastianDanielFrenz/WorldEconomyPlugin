package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.Machine;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.recipes.MachineRecipe;

public enum ItemCategory {

	// RAW_MATERIALS(CustomItem.IRON_INGOT.base_material),
	RAW_MATERIALS(Material.IRON_INGOT),
	PROCESSED_MATERIALS(Material.PAPER),
	MACHINES(Material.FURNACE),
	EQUIPMENT(Material.IRON_PICKAXE),
	TECHNOLOGY(Material.REDSTONE),
	FOOD(Material.COOKED_BEEF),
	BUILDING(Material.CONCRETE_POWDER),
	OTHER(Material.BUCKET);

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
