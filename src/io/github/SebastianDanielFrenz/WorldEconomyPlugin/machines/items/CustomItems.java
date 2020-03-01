package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class CustomItems {
	public static ChatColor TIER1 = ChatColor.GREEN;
	
	
	//Iron Items
	public static CustomItem IRON_INGOT = new CustomItem(Material.IRON_INGOT);
	public static CustomItem IRON_PLATE = new CustomItem(Material.PAPER, TIER1 + "Iron Plate");
	public static CustomItem IRON_ROD = new CustomItem(Material.IRON_NUGGET, TIER1 + "Iron Rod");
	
	//Steel Items
	public static CustomItem STEEL_INGOT = new CustomItem(Material.IRON_INGOT, TIER1 + "Steel Ingot"); //enchanted
	public static CustomItem STEEL_ROD = new CustomItem(Material.IRON_NUGGET, TIER1 + "Steel Rod" ); //enchanted
	public static CustomItem STEEL_PLATE = new CustomItem(Material.PAPER, TIER1 + "Steel Plate");//enchanted
	
	//Copper Items
	
	
	//Silikon
}
