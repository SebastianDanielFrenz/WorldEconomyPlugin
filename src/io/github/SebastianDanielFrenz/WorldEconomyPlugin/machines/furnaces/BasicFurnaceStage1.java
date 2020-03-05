package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces;

import org.bukkit.Location;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Units;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.recipes.MachineRecipe;

public class BasicFurnaceStage1 extends BasicFurnace {

	public BasicFurnaceStage1(Location location) {
		super(location);
	}

	@Override
	public double getMaintenanceCost() {
		return 1;
	}

	@Override
	public double getMaintenanceFrequency() {
		return Units.HOUR;
	}

	@Override
	public MachineRecipe[] getRecipes() {
		return super.getRecipes();
	}

	@Override
	public double getProcessTimeMultiplier() {
		return 1;
	}

}
