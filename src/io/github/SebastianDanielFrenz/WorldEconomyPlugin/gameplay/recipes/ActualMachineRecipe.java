package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.recipes;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.Machine;

public class ActualMachineRecipe {

	public MachineRecipe recipe;
	public Machine machine;

	public ActualMachineRecipe(MachineRecipe recipe, Machine machine) {
		this.recipe = recipe;
		this.machine = machine;
	}

}
