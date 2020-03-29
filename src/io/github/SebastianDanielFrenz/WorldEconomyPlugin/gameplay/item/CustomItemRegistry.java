package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomToolType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemAsh;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCharcoal;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCoal;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCoalOre;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCopperIngot;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCopperOre;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCopperPlate;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemIronBoots;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemIronChestplate;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemIronHelmet;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemIronIngot;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemIronLeggings;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemIronOre;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemIronPickaxe;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemIronPlate;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemIronRod;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemRedstone;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemSteelIngot;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemSteelPlate;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemSteelRod;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.blast_furnaces.BasicBlastFurnaceStage1;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces.BasicFurnaceStage1;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces.BasicFurnaceStage2;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces.BasicFurnaceStage3;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.sieves.BasicSieveStage1;

public class CustomItemRegistry {

	private static List<CustomItem> items = new ArrayList<CustomItem>();

	public static void register(CustomItem item) {
		items.add(item);
	}

	public static final CustomItem coalOre = new ItemCoalOre();
	public static final CustomItem coal = new ItemCoal();
	public static final CustomItem charcoal = new ItemCharcoal();
	public static final CustomItem ash = new ItemAsh();
	public static final CustomItem ironOre = new ItemIronOre();
	public static final CustomItem ironIngot = new ItemIronIngot();
	public static final CustomItem ironPlate = new ItemIronPlate();
	public static final CustomItem ironRod = new ItemIronRod();
	public static final CustomItem ironPickaxe = new ItemIronPickaxe();
	public static final CustomItem ironHelmet = new ItemIronHelmet();
	public static final CustomItem ironChestplate = new ItemIronChestplate();
	public static final CustomItem ironLeggings = new ItemIronLeggings();
	public static final CustomItem ironBoots = new ItemIronBoots();

	// Redstone
	public static final CustomItem redstone = new ItemRedstone();

	// Steel
	public static final CustomItem steel_ingot = new ItemSteelIngot();
	public static final CustomItem steel_plate = new ItemSteelPlate();
	public static final CustomItem steel_rod = new ItemSteelRod();

	// Copper Items
	public static final CustomItem copper_ore = new ItemCopperOre();
	public static final CustomItem copper_ingot = new ItemCopperIngot();
	public static final CustomItem copper_plate = new ItemCopperPlate();
	public static final CustomItem copper_rod = new ItemCopperRod();

	public static void init() {
		register(coalOre);
		register(coal);
		register(charcoal);
		
		register(ash);
		
		register(ironOre);
		register(ironIngot);
		register(ironPlate);
		register(ironRod);

		register(ironPickaxe);
		register(ironHelmet);
		register(ironChestplate);
		register(ironLeggings);
		register(ironBoots);

		register(redstone);

		// Steel Items
		register(steel_ingot);
		register(steel_plate);
		register(steel_rod);

		// Copper Items
		register(copper_ore);
		register(copper_ingot);
		register(copper_plate);
		register(copper_rod);

		// bronze items
		BRONZE_INGOT(Material.IRON_INGOT, Tier.TIER1, "Bronze Ingot", ItemCategory.RAW_MATERIALS),
		BRONZE_PLATE(Material.PAPER, Tier.TIER1, "Bronze Plate", ItemCategory.PROCESSED_MATERIALS),
		BRONZE_ROD(Material.STICK, Tier.TIER1, "Bronze Rod", ItemCategory.PROCESSED_MATERIALS),

		// aluminum
		ALUMINUM_INGOT(Material.IRON_INGOT, Tier.TIER1, "Aluminum Ingot", ItemCategory.RAW_MATERIALS),
		ALUMINUM_PLATE(Material.PAPER, Tier.TIER1, "Aluminum Plate", ItemCategory.PROCESSED_MATERIALS),
		ALUMINUM_ROD(Material.STICK, Tier.TIER1, "Aluminum Rod", ItemCategory.PROCESSED_MATERIALS),

		// TIN
		TIN_INGOT(Material.IRON_INGOT, Tier.TIER1, "Tin Ingot", ItemCategory.RAW_MATERIALS),
		TIN_PLATE(Material.PAPER, Tier.TIER1, "Tin Plate", ItemCategory.PROCESSED_MATERIALS),
		TIN_ROD(Material.STICK, Tier.TIER1, "Tin Rod", ItemCategory.PROCESSED_MATERIALS),

		// osmium
		OSMIUM_INGOT(Material.IRON_INGOT, Tier.TIER1, "Osmium Ingot", ItemCategory.RAW_MATERIALS),
		OSMIUM_PLATE(Material.PAPER, Tier.TIER1, "Osmium Plate", ItemCategory.PROCESSED_MATERIALS),
		OSMIUM_ROD(Material.STICK, Tier.TIER1, "Osmium Rod", ItemCategory.PROCESSED_MATERIALS),

		// lead
		LEAD_INGOT(Material.IRON_INGOT, Tier.TIER1, "Lead Ingot", ItemCategory.RAW_MATERIALS),
		LEAD_PLATE(Material.PAPER, Tier.TIER1, "Lead Plate", ItemCategory.PROCESSED_MATERIALS),
		LEAD_ROD(Material.STICK, Tier.TIER1, "Lead Rod", ItemCategory.PROCESSED_MATERIALS),

		// gold
		GOLD_ORE(Material.GOLD_ORE, Tier.TIER2, "Gold Ore", ItemCategory.RAW_MATERIALS, true),
		GOLD_NUGGET(Material.GOLD_NUGGET, Tier.TIER2, "Gold Nugget", ItemCategory.RAW_MATERIALS, true),
		GOLD_INGOT(Material.GOLD_INGOT, Tier.TIER2, "Gold Ingot", ItemCategory.RAW_MATERIALS, true),
		GOLD_ROD(Material.STICK, Tier.TIER2, "Gold Rod", ItemCategory.PROCESSED_MATERIALS),

		GOLD_CABLE(Material.GOLD_NUGGET, Tier.TIER2, "Gold Cable", ItemCategory.TECHNOLOGY),

		// silicon

		// diamond
		DIAMOND(Material.DIAMOND, Tier.TIER1, "Diamond", ItemCategory.RAW_MATERIALS, true),
		DIAMOND_ORE(Material.DIAMOND_ORE, Tier.TIER1, "Diamond Ore", ItemCategory.RAW_MATERIALS, true),

		// lapis lazuli
		LAPIS_LAZULI(Material.LAPIS_LAZULI, Tier.TIER1, "Lapis Lazuli", ItemCategory.RAW_MATERIALS, true),
		LAPIS_LAZULI_ORE(Material.LAPIS_ORE, Tier.TIER1, "Lapis Lazuli Ore", ItemCategory.RAW_MATERIALS, true),

		// materials
		CLAY_BALL(Material.CLAY_BALL, Tier.TIER1, "Clay Ball", ItemCategory.RAW_MATERIALS, true),
		RAW_HARDENED_COBBLESTONE(Material.COBBLESTONE, Tier.TIER1, "Raw Hardened Cobblestone",
				ItemCategory.PROCESSED_MATERIALS),
		RAW_HARDENED_COBBLESTONE_PLATE(Material.PAPER, Tier.TIER1, "Raw Hardened Cobblestone Plate",
				ItemCategory.PROCESSED_MATERIALS),
		HARDENED_COBBLESTONE(Material.COBBLESTONE, Tier.TIER1, "Hardened Cobblestone", ItemCategory.PROCESSED_MATERIALS),
		HARDENED_COBBLESTONE_PLATE(Material.PAPER, Tier.TIER1, "Hardened Cobblestone Plate",
				ItemCategory.PROCESSED_MATERIALS),
		PROCESSED_COBBLESTONE(Material.STONE, Tier.TIER1, "Processed Cobblestone", ItemCategory.PROCESSED_MATERIALS),
		PROCESSED_COBBLESTONE_PLATE(Material.PAPER, Tier.TIER1, "Processed Cobblestone Plate",
				ItemCategory.PROCESSED_MATERIALS),

		// fluids
		OIL_BUCKET(Material.LAVA_BUCKET, Tier.TIER1, "Oil Bucket", ItemCategory.RAW_MATERIALS),

		// buckets
		BUCKET(Material.BUCKET, Tier.TIER1, "Bucket", ItemCategory.OTHER, true),
		WATER_BUCKET(Material.WATER_BUCKET, Tier.TIER1, "Water Bucket", ItemCategory.OTHER, true),

		// clay buckets
		RAW_CLAY_BUCKET(Material.BUCKET, Tier.TIER1, "Raw Clay Bucket", ItemCategory.OTHER),
		CLAY_BUCKET(Material.BUCKET, Tier.TIER1, "Clay Bucket", ItemCategory.OTHER),
		WATER_CLAY_BUCKET(Material.WATER_BUCKET, Tier.TIER1, "Water Clay Bucket", ItemCategory.OTHER),

		// machines
		// furnaces
		BASIC_FURNACE_STAGE1(new BasicFurnaceStage1(null).getKategory().display, Tier.TIER1, "Basic Furnace Stage 1",
				ItemCategory.MACHINES),
		BASIC_FURNACE_STAGE2(new BasicFurnaceStage2(null).getKategory().display, Tier.TIER1, "Basic Furnace Stage 2",
				ItemCategory.MACHINES),
		BASIC_FURNACE_STAGE3(new BasicFurnaceStage3(null).getKategory().display, Tier.TIER1, "Basic Furnace Stage 3",
				ItemCategory.MACHINES),
		// sieves
		BASIC_SIEVE_STAGE1(new BasicSieveStage1(null).getKategory().display, Tier.TIER1, "Basic Sieve Stage 1",
				ItemCategory.MACHINES),
		// blast furnaces
		BASIC_BLAST_FURNACE_STAGE1(new BasicBlastFurnaceStage1(null).getKategory().display, Tier.TIER1,
				"Basic Blast Furnace Stage 1", ItemCategory.MACHINES),
		// camp fire
		CAMPFIRE(Material.CAMPFIRE, Tier.TIER1, "Campfire", ItemCategory.MACHINES, true),

		// tools
		// shovels
		WOODEN_SHOVEL(Material.WOODEN_SHOVEL, Tier.TIER1, "Wooden Shovel", ItemCategory.EQUIPMENT,
				new ItemDetail[] { new ToolItemDetail(CustomToolType.SHOVEL, CustomMaterialLevel.WOOD) }),
		COBBLESTONE_SHOVEL(Material.STONE_SHOVEL, Tier.TIER1, "Cobblestone Shovel", ItemCategory.EQUIPMENT,
				new ItemDetail[] { new ToolItemDetail(CustomToolType.SHOVEL, CustomMaterialLevel.COBBLESTONE) }),
		HARDENED_COBBLESTONE_SHOVEL(Material.STONE_SHOVEL, Tier.TIER1, "Hardened Cobblestone Shovel",
				ItemCategory.EQUIPMENT,
				new ItemDetail[] { new ToolItemDetail(CustomToolType.SHOVEL, CustomMaterialLevel.HARDENED_COBBLESTONE) }),
		PROCESSED_COBBLESTONE_SHOVEL(Material.STONE_SHOVEL, Tier.TIER1, "Processed Cobblestone Shovel",
				ItemCategory.EQUIPMENT,
				new ItemDetail[] { new ToolItemDetail(CustomToolType.SHOVEL, CustomMaterialLevel.PROCESSED_COBBLESTONE) }),

		// pickaxes
		PROCESSED_COBBLESTONE_PICKAXE(Material.STONE_PICKAXE, Tier.TIER1, "Processed Cobblestone Pickaxe",
				ItemCategory.EQUIPMENT,
				new ItemDetail[] { new ToolItemDetail(CustomToolType.PICKAXE, CustomMaterialLevel.PROCESSED_COBBLESTONE) }),

		// materials
		STICK(Material.STICK, Tier.TIER1, "Stick", ItemCategory.PROCESSED_MATERIALS, true),

		// Vanilla Items
		STONE(Material.STONE, Tier.TIER1, "Stone", ItemCategory.OTHER, true),
		GRASS_BLOCK(Material.GRASS_BLOCK, Tier.TIER1, "Grass Block", ItemCategory.OTHER, true),
		DIRT(Material.DIRT, Tier.TIER1, "Dirt", ItemCategory.OTHER, true),
		COBBLESTONE(Material.COBBLESTONE, Tier.TIER1, "Cobblestone", ItemCategory.OTHER, true),
		OAK_PLANKS(Material.OAK_PLANKS, Tier.TIER1, "Oak Planks", ItemCategory.OTHER, true),
		OAK_LOG(Material.OAK_LOG, Tier.TIER1, "Oak Log", ItemCategory.OTHER, true),
		COARSE_DIRT(Material.COARSE_DIRT, Tier.TIER1, "Coarse Dirt", ItemCategory.OTHER, true),
		SAND(Material.SAND, Tier.TIER1, "Sand", ItemCategory.RAW_MATERIALS, true),
		GRANITE(Material.GRANITE, Tier.TIER1, "Granite", ItemCategory.RAW_MATERIALS, true),
		POLISHED_GRANITE(Material.POLISHED_GRANITE, Tier.TIER1, "Polished Granite", ItemCategory.PROCESSED_MATERIALS, true),
		DIORITE(Material.DIORITE, Tier.TIER1, "Diorite", ItemCategory.RAW_MATERIALS, true),
		POLISHED_DIORITE(Material.POLISHED_DIORITE, Tier.TIER1, "Polished Diorite", ItemCategory.PROCESSED_MATERIALS, true),
		ANDESITE(Material.ANDESITE, Tier.TIER1, "Andesite", ItemCategory.RAW_MATERIALS, true),
		POLISHED_ANDESITE(Material.POLISHED_ANDESITE, Tier.TIER1, "Polished Andesite", ItemCategory.PROCESSED_MATERIALS,
				true),

		// food
		BERRIES(Material.SWEET_BERRIES, Tier.TIER1, "Berries", ItemCategory.FOOD, true);
	}

	public static List<CustomItem> getContents() {
		return items;
	}

}
