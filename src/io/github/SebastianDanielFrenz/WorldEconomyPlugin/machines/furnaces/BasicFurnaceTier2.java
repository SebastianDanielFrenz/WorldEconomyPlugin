package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineRecipe;

public class BasicFurnaceTier2 extends BasicFurnaceTier1{

	@Override
	public double getMaintenanceCost() {
		return 1;
	}

	@Override
	public double getMaintenanceFrequency() {
		return 60 * 65;
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
