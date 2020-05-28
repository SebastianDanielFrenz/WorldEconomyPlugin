package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.recipes;

import java.util.ArrayList;
import java.util.List;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.Machine;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.MachineRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchProfile;

public class MachineRecipeManager {

	public static List<ActualMachineRecipe> getRecipesByOutput(CustomItemStack itemStack,
			ResearchProfile researchProfile) {
		List<ActualMachineRecipe> out = new ArrayList<ActualMachineRecipe>();

		for (Machine machine : MachineRegistry.getContents()) {
			if (researchProfile.hasResearched(ResearchItemRegistry.getItemFor(machine))) {
				for (MachineRecipe recipe : machine.getRecipes()) {
					if (researchProfile.hasResearched(ResearchItemRegistry.getItemFor(recipe))) {
						out.add(new ActualMachineRecipe(recipe, machine));
					}
				}
			}
		}

		return out;
	}

}
