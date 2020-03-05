package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.recipes;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineRecipe;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItems;

public class RecipesRegistry {
	
	//made by afk worker
	
	//Coal proccesing
	public static MachineRecipe CRUSHING____COAL_ORE____COAL__COBBLESTONE = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItems.COAL_ORE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItems.COAL, 1), new CustomItemStack(CustomItems.COBBLESTONE, 1) }, 20);
	
	
	//Iron proccesing
	public static MachineRecipe SMELTING____IRON_ORE____IRON_INGOT = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItems.IRON_ORE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItems.IRON_INGOT, 1) }, 30);
	
	public static MachineRecipe FORGING____IRON_INGOT____IRON_PLATE = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItems.IRON_INGOT, 4) },
			new CustomItemStack[] { new CustomItemStack(CustomItems.IRON_PLATE, 1) }, 30);
	
	public static MachineRecipe FORGING____IRON_INGOT____IRON_ROD = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItems.IRON_INGOT, 2) },
			new CustomItemStack[] { new CustomItemStack(CustomItems.IRON_ROD, 1) }, 30);
	
	public static MachineRecipe FORGING____IRON_PLATE__IRON_ROD____IRON_PICKAXE = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItems.IRON_ROD, 4), new CustomItemStack(CustomItems.IRON_PLATE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItems.IRON_PICKAXE, 1) }, 600);
	
	public static MachineRecipe FORGING____IRON_PLATE____IRON_HELMT = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItems.IRON_PLATE, 2) },
			new CustomItemStack[] { new CustomItemStack(CustomItems.IRON_HELMET, 1) }, 600);
	
	
	
	
	//Gold proccesing
	public static MachineRecipe SMELTING____GOLD_ORE____GOLD_NUGGET = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItems.GOLD_ORE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItems.GOLD_NUGGET, 1) }, 60);
	
	public static MachineRecipe SMELTING___GOLD_NUGGET___GOLD_INGOT = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItems.GOLD_NUGGET, 10) },
			new CustomItemStack[] { new CustomItemStack(CustomItems.GOLD_INGOT, 1) }, 30);
	
	public static MachineRecipe FORGING___GOLD_INGOT___GOLD_ROD = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItems.GOLD_INGOT, 2) },
			new CustomItemStack[] { new CustomItemStack(CustomItems.GOLD_ROD, 1) }, 30);
	
	public static MachineRecipe CRAFTING___GOLD_ROD___GOLD_CABLE = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItems.GOLD_ROD, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItems.GOLD_CABLE, 10) }, 30);
	
	//dirt processing
	public static MachineRecipe SIEVING____COARSE_DIRT____COBBLESTONE__DIRT = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItems.COARSE_DIRT, 16) }, new CustomItemStack[] {
					new CustomItemStack(CustomItems.COBBLESTONE, 1), new CustomItemStack(CustomItems.DIRT, 16) },
			12);
	
}
