package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.Tiers;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.recipes.MachineRecipe;

public abstract class BasicFurnace extends MachineFurnace {
	public BasicFurnace(Location location) {
		super(location);
	}

	@Override
	public MachineRecipe[] getRecipes() {
		return new MachineRecipe[] {};
	}

	@Override
	public String getTierName() {
		return Tiers.TIER1 + "Basic Furnace";
	}
}
