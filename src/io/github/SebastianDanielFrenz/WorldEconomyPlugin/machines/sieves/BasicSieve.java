package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.sieves;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.Tiers;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.recipes.MachineRecipe;

public abstract class BasicSieve extends Sieve {

	public BasicSieve(Location location) {
		super(location);
	}

	@Override
	public MachineRecipe[] getRecipes() {
		return new MachineRecipe[] { MachineRecipe.SIEVING____COARSE_DIRT____COBBLESTONE__DIRT };
	}

	@Override
	public String getTierName() {
		return Tiers.TIER1 + "Basic Sieve";
	}

}
