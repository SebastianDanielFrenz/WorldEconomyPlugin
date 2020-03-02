package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class CustomItems {
	public static ChatColor TIER1 = ChatColor.GREEN;
	public static ChatColor TIER2 = ChatColor.YELLOW;
	public static ChatColor TIER3 = ChatColor.GOLD;

	// Iron Items
	public static CustomItem IRON_INGOT = new CustomItem(Material.IRON_INGOT);
	public static CustomItem IRON_PLATE = new CustomItem(Material.PAPER, TIER1 + "Iron Plate");
	public static CustomItem IRON_ROD = new CustomItem(Material.IRON_NUGGET, TIER1 + "Iron Rod");

	// Steel Items
	public static CustomItem STEEL_INGOT = new CustomItem(Material.IRON_INGOT, TIER3 + "Steel Ingot"); // enchanted
	public static CustomItem STEEL_PLATE = new CustomItem(Material.PAPER, TIER3 + "Steel Plate");// enchanted
	public static CustomItem STEEL_ROD = new CustomItem(Material.IRON_NUGGET, TIER3 + "Steel Rod"); // enchanted

	// Copper Items
	public static CustomItem COPPER_INGOT = new CustomItem(Material.GOLD_INGOT, TIER1 + "Copper Ingot");// enchanted
	public static CustomItem COPPER_PLATE = new CustomItem(Material.PAPER, TIER1 + "Copper Plate");// enchanted

	// bronze items
	public static CustomItem BRONZE_INGOT = new CustomItem(Material.IRON_INGOT, TIER1 + "Bronze Ingot");
	public static CustomItem BRONZE_PLATE = new CustomItem(Material.PAPER, TIER1 + "Bronze Plate");
	public static CustomItem BRONZE_ROD = new CustomItem(Material.IRON_NUGGET, TIER1 + "Bronze Rod");

	// aluminium
	public static CustomItem ALUMINUM_INGOT = new CustomItem(Material.IRON_INGOT, TIER1 + "Aluminum Ingot");
	public static CustomItem ALUMINUM_PLATE = new CustomItem(Material.PAPER, TIER1 + "Aluminum Plate");
	public static CustomItem ALUMINUM_ROD = new CustomItem(Material.IRON_NUGGET, TIER1 + "Aluminum Rod");

	// tinn
	public static CustomItem TINN_INGOT = new CustomItem(Material.IRON_INGOT, TIER1 + "Tinn Ingot");
	public static CustomItem TINN_PLATE = new CustomItem(Material.PAPER, TIER1 + "Tinn Plate");
	public static CustomItem TINN_ROD = new CustomItem(Material.IRON_NUGGET, TIER1 + "Tinn Rod");
	
	//osmium
		public static CustomItem OSMIUM_INGOT = new CustomItem(Material.IRON_INGOT, TIER1 + "Osmium Ingot");
		public static CustomItem OSMIUM_PLATE = new CustomItem(Material.PAPER, TIER1 + "Osmium Plate");
		public static CustomItem OSMIUM_ROD = new CustomItem(Material.IRON_NUGGET, TIER1 + "Osmium Rod");

	// Silikon

}
