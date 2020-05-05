package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machines.crafting_tables;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.recipes.MachineRecipe;

public class BlockStoneAgeCraftingTable extends MachineCraftingTable {

	public BlockStoneAgeCraftingTable() {
		super("stone_age_crafting_table", true, easyDrop(CustomItemRegistry.STONE_AGE_CAMPFIRE));
	}

	@Override
	public double getMaintenanceCost() {
		return 0;
	}

	@Override
	public double getMaintenanceFrequency() {
		return Double.MAX_VALUE;
	}

	@Override
	public MachineRecipe[] getRecipes() {
		return new MachineRecipe[] {};
	}

	@Override
	public double getProcessTimeMultiplier() {
		return 1;
	}

	@Override
	public String getName() {
		return null;
	}

}
