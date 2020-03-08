package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.recipes.MachineRecipe;

public abstract class AdvancedFurnace extends MachineFurnace {

	public AdvancedFurnace(Location location) {
		super(location);
	}

	@Override
	public MachineCategory getKategory() {
		return MachineCategory.SMELTING;
	}

	@Override
	public MachineRecipe[] getRecipes() {
		return new MachineRecipe[] { MachineRecipe.SMELTING____GOLD_ORE____GOLD_NUGGET };
	}

}
