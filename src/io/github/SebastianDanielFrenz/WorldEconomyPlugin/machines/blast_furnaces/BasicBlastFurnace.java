package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.blast_furnaces;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.recipes.MachineRecipe;

public abstract class BasicBlastFurnace extends BlastFurnace {

	public BasicBlastFurnace(Location location) {
		super(location);
	}

	@Override
	public String getTierName() {
		return "Basic Blast Furnace";
	}

	@Override
	public MachineRecipe[] getRecipes() {
		return new MachineRecipe[] { MachineRecipe.BLAST_FURNACE____IRON_ORE____IRON_INGOT };
	}

}
