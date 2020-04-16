package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.recipes;

import java.util.ArrayList;
import java.util.List;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.Machine;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.MachineRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;

public class MachineRecipeManager {

	public static List<ActualMachineRecipe> getRecipesByOutput(CustomItemStack itemStack) {
		List<ActualMachineRecipe> out = new ArrayList<ActualMachineRecipe>();

		for (Machine machine : MachineRegistry.getContents()) {
			machine.getCategory();
			// not actual code
		}

		return out;
	}

}
