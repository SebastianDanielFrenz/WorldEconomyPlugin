package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces;

import org.bukkit.Location;
import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineRecipe;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.Tiers;

public abstract class BasicFurnace extends MachineFurnace {
	public BasicFurnace(Location location) {
		super(location);
	}

	@Override
	public MachineRecipe[] getRecipes() {
		return new MachineRecipe[] {
				new MachineRecipe(new CustomItemStack[] { new CustomItemStack(CustomItem.COAL, 1) },
						new CustomItemStack[] { new CustomItemStack(CustomItem.CHARCOAL, 2) }, 1.0) };
	}

	@Override
	public String getTierName() {
		return Tiers.TIER1 + "Basic Furnace";
	}
}
