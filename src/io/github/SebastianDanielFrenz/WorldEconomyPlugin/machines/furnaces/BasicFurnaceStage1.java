package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineRecipe;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItemStack;

public class BasicFurnaceStage1 extends BasicFurnace {

	public BasicFurnaceStage1(Location location) {
		super(location);
	}

	@Override
	public double getMaintenanceCost() {
		return super.getMaintenanceCost() * 1;
	}

	@Override
	public double getMaintenanceFrequency() {
		return super.getMaintenanceFrequency() * 1;
	}

	@Override
	public MachineRecipe[] getRecipes() {
		return super.getRecipes();
	}

	@Override
	public double getProcessTimeMultiplier() {
		return super.getProcessTimeMultiplier() * 1;
	}

}
