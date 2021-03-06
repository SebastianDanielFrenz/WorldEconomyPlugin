package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.recipes;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Units;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchableObject;

/**
 * If you create new MachineRecipes, be it here or in an addon to the plugin,
 * please make sure to use the same object reference (same pointer) to refer to
 * the recipe for all machines of at least one type.
 * 
 * @author crash
 *
 */
public enum MachineRecipe implements ResearchableObject {

	CRAFTING____STICK____PLANKS(new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.STICK, 8) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.OAK_PLANKS, 1) }, 2),

	CRAFTING____COBBLESTONE__STICK____COBBLESTONE_SHOVEL(
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.COBBLESTONE, 2) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.STICK, 1) }, 5),

	CRAFTING____CLAY____CLAY_BUCKET(new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.CLAY_BALL, 8) },

			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.RAW_CLAY_BUCKET, 1) }, 5),
	CRAFTING____COBBLESTONE____CAMPFIRE(
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.COBBLESTONE, 4) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.STICK, 1) }, 5),

	CRAFTING____COBBLESTONE__SAND__WATER_CLAY_BUCKET____RAW_HARDENED_COBBLESTONE(
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.COBBLESTONE, 1),
					new CustomItemStack(CustomItemRegistry.SAND, 1),
					new CustomItemStack(CustomItemRegistry.WATER_CLAY_BUCKET, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.STICK, 1) }, 5),

	CRAFTING____RAW_HARDENED_COBBLESTONE____RAW_HARDENED_COBBLESTONE_PLATE(
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.RAW_HARDENED_COBBLESTONE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.RAW_HARDENED_COBBLESTONE_PLATE, 8) }, 5),

	CAMPFIRE____RAW_HARDENED_COBBLESTONE_PLATE____HARDENED_COBBLESTONE_PLATE(
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.RAW_HARDENED_COBBLESTONE_PLATE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.HARDENED_COBBLESTONE_PLATE, 1) },
			5 * Units.MINUTE),

	CAMPFIRE____RAW_HARDENED_COBBLESTONE____HARDENED_COBBLESTONE(
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.RAW_HARDENED_COBBLESTONE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.HARDENED_COBBLESTONE, 1) },
			5 * Units.MINUTE),

	SMELTING____HARDENED_COBBLESTONE____PROCESSED_COBBLESTONE(
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.HARDENED_COBBLESTONE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.PROCESSED_COBBLESTONE, 1) }, 20),

	SMELTING____HARDENED_COBBLESTONE_PLATE____PROCESSED_COBBLESTONE_PLATE(
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.HARDENED_COBBLESTONE_PLATE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.PROCESSED_COBBLESTONE_PLATE, 1) }, 20),

	CRAFTING____PROCESSED_COBBLESTONE_PLATE__STICK____PROCESSED_COBBLESTONE_PICKAXE(
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.PROCESSED_COBBLESTONE_PLATE, 3),
					new CustomItemStack(CustomItemRegistry.STICK, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.PROCESSED_COBBLESTONE_PICKAXE, 1) }, 5),

	// Coal processing
	CRUSHING____COAL_ORE____COAL__COBBLESTONE(
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.COAL_ORE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.COAL, 1),
					new CustomItemStack(CustomItemRegistry.COBBLESTONE, 1) },
			20),

	// Iron processing
	BLAST_FURNACE____IRON_ORE____IRON_INGOT(
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.IRON_ORE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.IRON_INGOT, 1) }, 30),

	FORGING____IRON_INGOT____IRON_PLATE(new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.IRON_INGOT, 4) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.IRON_PLATE, 1) }, 30),

	FORGING____IRON_INGOT____IRON_ROD(new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.IRON_INGOT, 2) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.IRON_ROD, 1) }, 30),

	FORGING____IRON_PLATE__IRON_ROD____IRON_PICKAXE(
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.IRON_ROD, 4),
					new CustomItemStack(CustomItemRegistry.IRON_PLATE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.IRON_PICKAXE, 1) }, 600),

	FORGING____IRON_PLATE____IRON_HELMT(new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.IRON_PLATE, 2) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.IRON_HELMET, 1) }, 600),

	// Gold processing
	SMELTING____GOLD_ORE____GOLD_NUGGET(new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.GOLD_ORE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.GOLD_NUGGET, 1) }, 60),

	SMELTING____GOLD_NUGGET____GOLD_INGOT(
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.GOLD_NUGGET, 10) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.GOLD_INGOT, 1) }, 30),

	FORGING____GOLD_INGOT____GOLD_ROD(new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.GOLD_INGOT, 2) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.GOLD_ROD, 1) }, 30),

	CRAFTING____GOLD_ROD____GOLD_CABLE(new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.GOLD_ROD, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.GOLD_CABLE, 10) }, 30),

	// dirt processing
	SIEVING____COARSE_DIRT____COBBLESTONE__DIRT(
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.COARSE_DIRT, 16) },
			new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.COBBLESTONE, 1),
					new CustomItemStack(CustomItemRegistry.DIRT, 16) },
			12);

	/*
	 * ====================
	 * 
	 * Machines
	 * 
	 * ====================
	 */

	private MachineRecipe(CustomItemStack[] input, CustomItemStack[] output, double processTime) {
		this.input = input;
		this.output = output;
		this.processTime = processTime;
	}

	private CustomItemStack[] input;
	private CustomItemStack[] output;
	private double processTime;
	private ResearchItem researchItem;

	public CustomItemStack[] getInput() {
		return input;
	}

	public void setInput(CustomItemStack[] input) {
		this.input = input;
	}

	public CustomItemStack[] getOutput() {
		return output;
	}

	public void setOutput(CustomItemStack[] output) {
		this.output = output;
	}

	public double getProcessTime() {
		return processTime;
	}

	public void setProcessTime(double processTime) {
		this.processTime = processTime;
	}

	public ResearchItem getResearchItem() {
		return researchItem;
	}
}
