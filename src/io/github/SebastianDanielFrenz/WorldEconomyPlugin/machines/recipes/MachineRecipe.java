package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.recipes;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItemStack;

public enum MachineRecipe {

	// made by afk worker
	
	

	// Coal processing
	CRUSHING____COAL_ORE____COAL__COBBLESTONE(
			new CustomItemStack[] { new CustomItemStack(CustomItem.COAL_ORE, 1) }, new CustomItemStack[] {
					new CustomItemStack(CustomItem.COAL, 1), new CustomItemStack(CustomItem.COBBLESTONE, 1) },
			20),

	// Iron processing
	SMELTING____IRON_ORE____IRON_INGOT(
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_ORE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_INGOT, 1) }, 30),

	FORGING____IRON_INGOT____IRON_PLATE(
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_INGOT, 4) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_PLATE, 1) }, 30),

	FORGING____IRON_INGOT____IRON_ROD(
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_INGOT, 2) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_ROD, 1) }, 30),

	FORGING____IRON_PLATE__IRON_ROD____IRON_PICKAXE(
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_ROD, 4),
					new CustomItemStack(CustomItem.IRON_PLATE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_PICKAXE, 1) }, 600),

	FORGING____IRON_PLATE____IRON_HELMT(
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_PLATE, 2) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_HELMET, 1) }, 600),

	// Gold proccesing
	SMELTING____GOLD_ORE____GOLD_NUGGET(
			new CustomItemStack[] { new CustomItemStack(CustomItem.GOLD_ORE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.GOLD_NUGGET, 1) }, 60),

	SMELTING____GOLD_NUGGET____GOLD_INGOT(
			new CustomItemStack[] { new CustomItemStack(CustomItem.GOLD_NUGGET, 10) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.GOLD_INGOT, 1) }, 30),

	FORGING____GOLD_INGOT____GOLD_ROD(
			new CustomItemStack[] { new CustomItemStack(CustomItem.GOLD_INGOT, 2) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.GOLD_ROD, 1) }, 30),

	CRAFTING____GOLD_ROD____GOLD_CABLE(
			new CustomItemStack[] { new CustomItemStack(CustomItem.GOLD_ROD, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.GOLD_CABLE, 10) }, 30),

	// dirt processing
	SIEVING____COARSE_DIRT____COBBLESTONE__DIRT(
			new CustomItemStack[] { new CustomItemStack(CustomItem.COARSE_DIRT, 16) }, new CustomItemStack[] {
					new CustomItemStack(CustomItem.COBBLESTONE, 1), new CustomItemStack(CustomItem.DIRT, 16) },
			12),;

	private MachineRecipe(CustomItemStack[] input, CustomItemStack[] output, double processTime) {
		this.input = input;
		this.output = output;
		this.processTime = processTime;
	}

	private CustomItemStack[] input;
	private CustomItemStack[] output;
	private double processTime;

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
}
