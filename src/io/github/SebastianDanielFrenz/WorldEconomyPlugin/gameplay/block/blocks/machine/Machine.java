package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machine;

import org.bukkit.Material;
import org.bukkit.block.Block;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockDropTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.recipes.MachineRecipe;

public abstract class Machine extends CustomBlockType {

	public Machine(String ID, Material material, boolean vanilla, CustomBlockDropTable drop_table,
			Class<? extends CustomBlockMachineData> blockDataType) {
		super(ID, material, vanilla, drop_table, blockDataType);
	}

	public static MachineRecipe[] mergeRecipes(MachineRecipe[] recipes1, MachineRecipe[] recipes2) {
		MachineRecipe[] recipes = new MachineRecipe[recipes1.length + recipes2.length];
		for (int i = 0; i < recipes1.length; i++) {
			recipes[i] = recipes1[i];
		}
		for (int i = 0; i < recipes2.length; i++) {
			recipes[i + recipes1.length] = recipes2[i];
		}

		return recipes;
	}

	public abstract MachineCategory getCategory();

	public abstract double getMaintenanceCost();

	public abstract double getMaintenanceFrequency();

	public abstract MachineRecipe[] getRecipes();

	public abstract double getProcessTimeMultiplier();

	public abstract String getName();

	public static boolean isMachine(Block block) {
		return block.getMetadata("customBlockType").size() > 0;
	}

}
