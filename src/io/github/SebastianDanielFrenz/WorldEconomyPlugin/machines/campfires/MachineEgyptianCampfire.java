package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.campfires;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.recipes.MachineRecipe;

public abstract class MachineEgyptianCampfire extends MachineCampfire {

	public MachineEgyptianCampfire(Location location) {
		super(location);
	}

	@Override
	public MachineRecipe[] getRecipes() {
		return new MachineRecipe[] { MachineRecipe.CAMPFIRE____RAW_HARDENED_COBBLESTONE____HARDENED_COBBLESTONE,
				MachineRecipe.CAMPFIRE____RAW_HARDENED_COBBLESTONE_PLATE____HARDENED_COBBLESTONE_PLATE };
	}

}
