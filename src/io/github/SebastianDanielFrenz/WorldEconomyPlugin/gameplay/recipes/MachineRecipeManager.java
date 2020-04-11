package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.recipes;

import java.util.ArrayList;
import java.util.List;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.Machine;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineRegistry;

public class MachineRecipeManager {

	public static List<ActualMachineRecipe> getRecipesByOutput(CustomItemStack itemStack) {
		List<ActualMachineRecipe> out = new ArrayList<ActualMachineRecipe>();

		for (Machine machine : MachineRegistry.getContents()) {
			machine.getInventory();
		}

		return out;
	}

}
