package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineKategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.recipes.MachineRecipe;

public abstract class AdvancedFurnace extends MachineFurnace {

	public AdvancedFurnace(Location location) {
		super(location);
	}

	@Override
	public MachineKategory getKategory() {
		return MachineKategory.SMELTING;
	}

	@Override
	public MachineRecipe[] getRecipes() {
		return new MachineRecipe[] { MachineRecipe.SMELTING____GOLD_ORE____GOLD_NUGGET };
	}

}
