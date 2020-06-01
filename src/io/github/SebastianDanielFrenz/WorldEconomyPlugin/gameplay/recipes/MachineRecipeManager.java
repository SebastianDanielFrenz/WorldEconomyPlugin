package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.recipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.AccelerationLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.AccelerationPotential;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.Machine;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.MachineRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchProfile;

public class MachineRecipeManager {

	/**
	 * ignores custom item data
	 * 
	 * @param itemStack
	 * @param researchProfile
	 * @return
	 */
	@AccelerationPotential(lvl = AccelerationLevel.LITTLE)
	public static Map<Machine, List<MachineRecipe>> getRecipesByOutput(CustomItem item,
			ResearchProfile researchProfile) {
		Map<Machine, List<MachineRecipe>> out = new TreeMap<Machine, List<MachineRecipe>>();

		boolean registeredMachine;
		for (Machine machine : MachineRegistry.getContents()) {
			registeredMachine = false;

			if (researchProfile.hasResearched(ResearchItemRegistry.getItemFor(machine))) {
				for (MachineRecipe recipe : machine.getRecipes()) {
					boolean all_researched = true;
					boolean contains_item = false;
					for (CustomItemStack stack : recipe.getOutput()) {
						if (!researchProfile.hasResearched(ResearchItemRegistry.getItemFor(stack.getItem()))) {
							all_researched = false;
							break;
						}
						if (stack.getItem() == item) {
							contains_item = true;
						}
					}

					if (all_researched && contains_item) {
						if (registeredMachine) {
							List<MachineRecipe> list = new ArrayList<MachineRecipe>();
							list.add(recipe);

							out.put(machine, list);
							registeredMachine = true;
						} else {
							out.get(machine).add(recipe);
						}
					}
				}
			}
		}

		return out;
	}

}
