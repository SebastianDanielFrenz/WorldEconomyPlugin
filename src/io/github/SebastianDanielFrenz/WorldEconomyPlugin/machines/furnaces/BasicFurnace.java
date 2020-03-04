package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces;

import org.bukkit.Location;
import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineRecipe;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItems;

public class BasicFurnace extends MachineFurnace {
	public BasicFurnace(Location location) {
		super(location);
	}

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
		return new MachineRecipe[] {
				new MachineRecipe(new CustomItemStack[] { new CustomItemStack(new CustomItem(Material.COAL), 1) },
						new CustomItemStack[] { new CustomItemStack(new CustomItem(Material.CHARCOAL), 2) }, 1.0) };
	}

	@Override
	public double getProcessTimeMultiplier() {
		return 1;
	}

	@Override
	public String getStageName() {
		return CustomItems.TIER1 + "Basic Furnace";
	}
}
