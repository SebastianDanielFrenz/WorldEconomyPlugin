package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machines.campfires;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockDropTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.recipes.MachineRecipe;

public abstract class MachineEgyptianCampfire extends MachineCampfire {

	public MachineEgyptianCampfire(String ID, CustomBlockDropTable drop_table) {
		super(ID, false, drop_table);
	}

	@Override
	public MachineRecipe[] getRecipes() {
		return new MachineRecipe[] { MachineRecipe.CAMPFIRE____RAW_HARDENED_COBBLESTONE____HARDENED_COBBLESTONE,
				MachineRecipe.CAMPFIRE____RAW_HARDENED_COBBLESTONE_PLATE____HARDENED_COBBLESTONE_PLATE };
	}

}
