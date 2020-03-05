package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.recipes;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineRecipe;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItem;

public class RecipesRegistry {

	// made by afk worker

	// Coal processing
	public static MachineRecipe CRUSHING____COAL_ORE____COAL__COBBLESTONE = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItem.COAL_ORE, 1) }, new CustomItemStack[] {
					new CustomItemStack(CustomItem.COAL, 1), new CustomItemStack(CustomItem.COBBLESTONE, 1) },
			20);

	// Iron processing
	public static MachineRecipe SMELTING____IRON_ORE____IRON_INGOT = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_ORE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_INGOT, 1) }, 30);

	public static MachineRecipe FORGING____IRON_INGOT____IRON_PLATE = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_INGOT, 4) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_PLATE, 1) }, 30);

	public static MachineRecipe FORGING____IRON_INGOT____IRON_ROD = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_INGOT, 2) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_ROD, 1) }, 30);

	public static MachineRecipe FORGING____IRON_PLATE__IRON_ROD____IRON_PICKAXE = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_ROD, 4),
					new CustomItemStack(CustomItem.IRON_PLATE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_PICKAXE, 1) }, 600);

	public static MachineRecipe FORGING____IRON_PLATE____IRON_HELMT = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_PLATE, 2) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.IRON_HELMET, 1) }, 600);

	// Gold proccesing
	public static MachineRecipe SMELTING____GOLD_ORE____GOLD_NUGGET = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItem.GOLD_ORE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.GOLD_NUGGET, 1) }, 60);

	public static MachineRecipe SMELTING____GOLD_NUGGET____GOLD_INGOT = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItem.GOLD_NUGGET, 10) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.GOLD_INGOT, 1) }, 30);

	public static MachineRecipe FORGING____GOLD_INGOT____GOLD_ROD = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItem.GOLD_INGOT, 2) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.GOLD_ROD, 1) }, 30);

	public static MachineRecipe CRAFTING____GOLD_ROD____GOLD_CABLE = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItem.GOLD_ROD, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItem.GOLD_CABLE, 10) }, 30);

	// dirt processing
	public static MachineRecipe SIEVING____COARSE_DIRT____COBBLESTONE__DIRT = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItem.COARSE_DIRT, 16) }, new CustomItemStack[] {
					new CustomItemStack(CustomItem.COBBLESTONE, 1), new CustomItemStack(CustomItem.DIRT, 16) },
			12);

}
