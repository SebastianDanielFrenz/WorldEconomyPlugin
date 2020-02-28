package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineFurnace;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineRecipe;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItemStack;

public class BasicFurnaceTier1 extends MachineFurnace {

	@Override
	public double getMaintenanceCost() {
		return 1;
	}

	@Override
	public double getMaintenanceFrequency() {
		return 60 * 60;
	}

	@Override
	public MachineRecipe[] getRecipes() {
		return new MachineRecipe[] { new MachineRecipe(new CustomItemStack[] {}, new CustomItemStack[] {}, 1.0) };
	}

	@Override
	public double getProcessTimeMultiplier() {
		return 1;
	}

}
