package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces;

import org.bukkit.Location;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Units;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.recipes.MachineRecipe;

public class BasicFurnaceStage2 extends BasicFurnace {

	public BasicFurnaceStage2(Location location) {
		super(location);
	}

	@Override
	public double getMaintenanceCost() {
		return 1.25;
	}

	@Override
	public double getMaintenanceFrequency() {
		return Units.MINUTE*50;
	}

	@Override
	public MachineRecipe[] getRecipes() {
		return super.getRecipes();
	}

	@Override
	public double getProcessTimeMultiplier() {
		return 0.95;
	}

}
