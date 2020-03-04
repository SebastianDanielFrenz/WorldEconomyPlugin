package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces;

import org.bukkit.Location;
import org.bukkit.inventory.Inventory;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineRecipe;

public class BasicFurnaceStage2 extends BasicFurnaceStage1 {

	public BasicFurnaceStage2(Location location) {
		super(location);
	}

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
