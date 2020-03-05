package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Units;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineRecipe;

public class BasicFurnaceStage3 extends BasicFurnace {

	public BasicFurnaceStage3(Location location) {
		super(location);
	}

	@Override
	public double getMaintenanceCost() {
		return 1.75;
	}

	@Override
	public double getMaintenanceFrequency() {
		return Units.MINUTE * 40;
	}

	@Override
	public MachineRecipe[] getRecipes() {
		return super.getRecipes();
	}

	@Override
	public double getProcessTimeMultiplier() {
		return 0.9;
	}
}
