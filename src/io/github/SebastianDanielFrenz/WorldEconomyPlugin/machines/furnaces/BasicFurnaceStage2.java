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
		return super.getMaintenanceCost() * 1.1;
	}

	@Override
	public double getMaintenanceFrequency() {
		return super.getMaintenanceFrequency() * 0.8;
	}

	@Override
	public MachineRecipe[] getRecipes() {
		return super.getRecipes();
	}

	@Override
	public double getProcessTimeMultiplier() {
		return super.getProcessTimeMultiplier() * 0.95;
	}

}
