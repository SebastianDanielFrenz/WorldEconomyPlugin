package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.recepies;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineRecipe;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItems;

public class RecipesRegistry {
	
	//made by afk worker
	
	//Coal proccesing
	public static MachineRecipe CRUSHING____COAL_ORE____COAL = new MachineRecipe(
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
	
	public static MachineRecipe FORGING____IRON_PLATE__IRON_ROD____IRON_INGOT = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItems.IRON_ROD, 4), new CustomItemStack(CustomItems.IRON_PLATE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItems.IRON_PICKAXE, 1) }, 300);
	
	
	
	
	//Gold proccesing
	public static MachineRecipe SMELTING____GOLD_ORE____GOLD_NUGGET = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItems.GOLD_ORE, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItems.GOLD_NUGGET, 1) }, 60);
	
	public static MachineRecipe SMELTING___GOLD_NUGGET___GOLD_INGOT = new MachineRecipe(
			new CustomItemStack[] { new CustomItemStack(CustomItems.GOLD_NUGGET, 1) },
			new CustomItemStack[] { new CustomItemStack(CustomItems.GOLD_INGOT, 10) }, 30);
	
}
