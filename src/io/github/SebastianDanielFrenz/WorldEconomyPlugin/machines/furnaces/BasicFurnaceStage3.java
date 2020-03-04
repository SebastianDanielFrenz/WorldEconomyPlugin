package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineRecipe;

public class BasicFurnaceStage3 extends BasicFurnaceStage2 {

	public BasicFurnaceStage3(Location location) {
		super(location);
	}

	@Override
	public double getMaintenanceCost() {
		return super.getMaintenanceCost() * 1.2;
	}

	@Override
	public double getMaintenanceFrequency() {
		return super.getMaintenanceFrequency() * 0.6;
	}

	@Override
	public MachineRecipe[] getRecipes() {
		return super.getRecipes();
	}

	@Override
	public double getProcessTimeMultiplier() {
		return super.getProcessTimeMultiplier() * 0.9;
	}
}
