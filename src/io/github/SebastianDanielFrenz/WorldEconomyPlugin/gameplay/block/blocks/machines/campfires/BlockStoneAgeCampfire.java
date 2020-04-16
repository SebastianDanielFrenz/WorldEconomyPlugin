package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machines.campfires;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Units;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.recipes.MachineRecipe;

public class BlockStoneAgeCampfire extends MachineCampfire {

	public BlockStoneAgeCampfire() {
		super("stone_age_campfire", true, easyDrop(CustomItemRegistry.CAMPFIRE));
	}

	@Override
	public double getMaintenanceCost() {
		return 0;
	}

	@Override
	public double getMaintenanceFrequency() {
		return Units.HOUR;
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
		// TODO Auto-generated method stub
		return null;
	}

}
