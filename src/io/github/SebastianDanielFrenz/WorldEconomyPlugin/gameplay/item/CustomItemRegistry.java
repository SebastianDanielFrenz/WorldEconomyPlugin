package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemAsh;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemBronzeRod;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCharcoal;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCoal;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCoalOre;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCopperIngot;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCopperOre;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCopperPlate;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCopperRod;
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
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemAluminumIngot;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemAluminumPlate;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemAluminumRod;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemTinIngot;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemTinPlate;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemTinRod;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemOsmiumIngot;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemOsmiumPlate;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemOsmiumRod;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemLeadIngot;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemLeadPlate;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemLeadRod;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemOakLeaves;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemGoldOre;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemGoldNugget;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemGoldIngot;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemGoldRod;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemGoldCable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemDiamond;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemDiamondOre;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemLapisLazuli;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemLapisLazuliOre;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemClayBall;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemRawHardenedCobblestone;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemRawHardenedCobblestonePlate;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemHardenedCobblestone;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemHardenedCobblestonePlate;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemProcessedCobblestone;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemProcessedCobblestonePlate;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemOilBucket;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemBucket;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemWaterBucket;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemRawClayBucket;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemClayBucket;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemWaterClayBucket;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemWoodenShovel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.admin_tools.ItemAdminSword;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCobblestoneShovel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemHardenedCobblestoneShovel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemProcessedCobblestoneShovel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemProcessedCobblestonePickaxe;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemStick;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemStone;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemStoneAgeCampfire;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemStoneAgeCraftingTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemGrassBlock;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemDirt;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemEgyptianCampfireStage1;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCobblestone;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemOakPlanks;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemOakSlab;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemOakLog;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCoarseDirt;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemSand;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemSandstoneTrigger;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemSharpStick;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemSmoothSandstone;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemGranite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemPolishedGranite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemDiorite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemPolishedDiorite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemAndesite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemPolishedAndesite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemBronzeIngot;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemBronzePlate;

public class CustomItemRegistry {

	private static List<CustomItem> items = new ArrayList<CustomItem>();

	public static void register(CustomItem item) {
		items.add(item);
	}

	public static void register(CustomItem item, CustomBlockType block) {
		register(item);
		((CustomPlaceableItem) item).setBlock(block);
	}

	public static final CustomItem COAL_ORE = new ItemCoalOre();
	public static final CustomItem COAL = new ItemCoal();
	public static final CustomItem CHARCOAL = new ItemCharcoal();
	public static final CustomItem ASH = new ItemAsh();
	public static final CustomItem IRON_ORE = new ItemIronOre();
	public static final CustomItem IRON_INGOT = new ItemIronIngot();
	public static final CustomItem IRON_PLATE = new ItemIronPlate();
	public static final CustomItem IRON_ROD = new ItemIronRod();
	public static final CustomItem IRON_PICKAXE = new ItemIronPickaxe();
	public static final CustomItem IRON_HELMET = new ItemIronHelmet();
	public static final CustomItem IRON_CHESTPLATE = new ItemIronChestplate();
	public static final CustomItem IRON_LEGGINGS = new ItemIronLeggings();
	public static final CustomItem IRON_BOOTS = new ItemIronBoots();

	// Redstone
	public static final CustomItem REDSTONE = new ItemRedstone();

	// Steel
	public static final CustomItem STEEL_INGOT = new ItemSteelIngot();
	public static final CustomItem STEEL_PLATE = new ItemSteelPlate();
	public static final CustomItem STEEL_ROD = new ItemSteelRod();

	// Copper Items
	public static final CustomItem COPPER_ORE = new ItemCopperOre();
	public static final CustomItem COPPER_INGOT = new ItemCopperIngot();
	public static final CustomItem COPPER_PLATE = new ItemCopperPlate();
	public static final CustomItem COPPER_ROD = new ItemCopperRod();

	public static final CustomItem BRONZE_INGOT = new ItemBronzeIngot();
	public static final CustomItem BRONZE_PLATE = new ItemBronzePlate();
	public static final CustomItem BRONZE_ROD = new ItemBronzeRod();
	public static final CustomItem ALUMINUM_INGOT = new ItemAluminumIngot();
	public static final CustomItem ALUMINUM_PLATE = new ItemAluminumPlate();
	public static final CustomItem ALUMINUM_ROD = new ItemAluminumRod();
	public static final CustomItem TIN_INGOT = new ItemTinIngot();
	public static final CustomItem TIN_PLATE = new ItemTinPlate();
	public static final CustomItem TIN_ROD = new ItemTinRod();
	public static final CustomItem OSMIUM_INGOT = new ItemOsmiumIngot();
	public static final CustomItem OSMIUM_PLATE = new ItemOsmiumPlate();
	public static final CustomItem OSMIUM_ROD = new ItemOsmiumRod();
	public static final CustomItem LEAD_INGOT = new ItemLeadIngot();
	public static final CustomItem LEAD_PLATE = new ItemLeadPlate();
	public static final CustomItem LEAD_ROD = new ItemLeadRod();
	public static final CustomItem GOLD_ORE = new ItemGoldOre();
	public static final CustomItem GOLD_NUGGET = new ItemGoldNugget();
	public static final CustomItem GOLD_INGOT = new ItemGoldIngot();
	public static final CustomItem GOLD_ROD = new ItemGoldRod();
	public static final CustomItem GOLD_CABLE = new ItemGoldCable();
	public static final CustomItem DIAMOND = new ItemDiamond();
	public static final CustomItem DIAMOND_ORE = new ItemDiamondOre();
	public static final CustomItem LAPIS_LAZULI = new ItemLapisLazuli();
	public static final CustomItem LAPIS_LAZULI_ORE = new ItemLapisLazuliOre();
	public static final CustomItem CLAY_BALL = new ItemClayBall();
	public static final CustomItem RAW_HARDENED_COBBLESTONE = new ItemRawHardenedCobblestone();
	public static final CustomItem RAW_HARDENED_COBBLESTONE_PLATE = new ItemRawHardenedCobblestonePlate();
	public static final CustomItem HARDENED_COBBLESTONE = new ItemHardenedCobblestone();
	public static final CustomItem HARDENED_COBBLESTONE_PLATE = new ItemHardenedCobblestonePlate();
	public static final CustomItem PROCESSED_COBBLESTONE = new ItemProcessedCobblestone();
	public static final CustomItem PROCESSED_COBBLESTONE_PLATE = new ItemProcessedCobblestonePlate();
	public static final CustomItem OIL_BUCKET = new ItemOilBucket();
	public static final CustomItem BUCKET = new ItemBucket();
	public static final CustomItem WATER_BUCKET = new ItemWaterBucket();
	public static final CustomItem RAW_CLAY_BUCKET = new ItemRawClayBucket();
	public static final CustomItem CLAY_BUCKET = new ItemClayBucket();
	public static final CustomItem WATER_CLAY_BUCKET = new ItemWaterClayBucket();
	public static final CustomItem WOODEN_SHOVEL = new ItemWoodenShovel();
	public static final CustomItem COBBLESTONE_SHOVEL = new ItemCobblestoneShovel();
	public static final CustomItem HARDENED_COBBLESTONE_SHOVEL = new ItemHardenedCobblestoneShovel();
	public static final CustomItem PROCESSED_COBBLESTONE_SHOVEL = new ItemProcessedCobblestoneShovel();
	public static final CustomItem PROCESSED_COBBLESTONE_PICKAXE = new ItemProcessedCobblestonePickaxe();
	public static final CustomItem STICK = new ItemStick();
	public static final CustomItem STONE = new ItemStone();
	public static final CustomItem GRASS_BLOCK = new ItemGrassBlock();
	public static final CustomItem DIRT = new ItemDirt();
	public static final CustomItem COBBLESTONE = new ItemCobblestone();
	public static final CustomItem OAK_PLANKS = new ItemOakPlanks();
	public static final CustomItem OAK_LOG = new ItemOakLog();
	public static final CustomItem OAK_SLAB = new ItemOakSlab();
	public static final CustomItem OAK_LEAVES = new ItemOakLeaves();
	public static final CustomItem COARSE_DIRT = new ItemCoarseDirt();
	public static final CustomItem SAND = new ItemSand();
	public static final CustomItem GRANITE = new ItemGranite();
	public static final CustomItem POLISHED_GRANITE = new ItemPolishedGranite();
	public static final CustomItem DIORITE = new ItemDiorite();
	public static final CustomItem POLISHED_DIORITE = new ItemPolishedDiorite();
	public static final CustomItem ANDESITE = new ItemAndesite();
	public static final CustomItem POLISHED_ANDESITE = new ItemPolishedAndesite();

	public static final CustomItem SANDSTONE_TRIGGER = new ItemSandstoneTrigger();
	public static final CustomItem SMOOTH_SANDSTONE = new ItemSmoothSandstone();

	public static final CustomItem STONE_AGE_CRAFTING_TABLE = new ItemStoneAgeCraftingTable();
	public static final CustomItem STONE_AGE_CAMPFIRE = new ItemStoneAgeCampfire();
	public static final CustomItem EGYPTIAN_CAMPFIRE_STAGE1 = new ItemEgyptianCampfireStage1();

	public static final CustomItem SHARP_STICK = new ItemSharpStick();
	
	// admin stuff
	
	public static final CustomItem ADMIN_SWORD = new ItemAdminSword();

	public static void init() {
		register(COAL_ORE);
		register(COAL);
		register(CHARCOAL);

		register(ASH);

		register(IRON_ORE);
		register(IRON_INGOT);
		register(IRON_PLATE);
		register(IRON_ROD);

		register(IRON_PICKAXE);
		register(IRON_HELMET);
		register(IRON_CHESTPLATE);
		register(IRON_LEGGINGS);
		register(IRON_BOOTS);

		register(REDSTONE);

		// Steel Items
		register(STEEL_INGOT);
		register(STEEL_PLATE);
		register(STEEL_ROD);

		// Copper Items
		register(COPPER_ORE, CustomBlockTypeRegistry.COPPER_ORE);
		register(COPPER_INGOT);
		register(COPPER_PLATE);
		register(COPPER_ROD);

		// bronze items
		register(BRONZE_INGOT);
		register(BRONZE_PLATE);
		register(BRONZE_ROD);
		register(ALUMINUM_INGOT);
		register(ALUMINUM_PLATE);
		register(ALUMINUM_ROD);
		register(TIN_INGOT);
		register(TIN_PLATE);
		register(TIN_ROD);
		register(OSMIUM_INGOT);
		register(OSMIUM_PLATE);
		register(OSMIUM_ROD);
		register(LEAD_INGOT);
		register(LEAD_PLATE);
		register(LEAD_ROD);
		register(GOLD_ORE);
		register(GOLD_NUGGET);
		register(GOLD_INGOT);
		register(GOLD_ROD);
		register(GOLD_CABLE);
		register(DIAMOND);
		register(DIAMOND_ORE);
		register(LAPIS_LAZULI);
		register(LAPIS_LAZULI_ORE);
		register(CLAY_BALL);
		register(RAW_HARDENED_COBBLESTONE);
		register(RAW_HARDENED_COBBLESTONE_PLATE);
		register(HARDENED_COBBLESTONE);
		register(HARDENED_COBBLESTONE_PLATE);
		register(PROCESSED_COBBLESTONE);
		register(PROCESSED_COBBLESTONE_PLATE);
		register(OIL_BUCKET);
		register(BUCKET);
		register(WATER_BUCKET);
		register(RAW_CLAY_BUCKET);
		register(CLAY_BUCKET);
		register(WATER_CLAY_BUCKET);
		register(WOODEN_SHOVEL);
		register(COBBLESTONE_SHOVEL);
		register(HARDENED_COBBLESTONE_SHOVEL);
		register(PROCESSED_COBBLESTONE_SHOVEL);
		register(PROCESSED_COBBLESTONE_PICKAXE);
		register(STICK);
		register(STONE);
		register(GRASS_BLOCK);
		register(DIRT);
		register(COBBLESTONE);
		register(OAK_PLANKS);
		register(OAK_LOG);
		register(OAK_SLAB);
		register(OAK_LEAVES);
		register(COARSE_DIRT, CustomBlockTypeRegistry.COARSE_DIRT);
		register(SAND, CustomBlockTypeRegistry.SAND);
		register(GRANITE, CustomBlockTypeRegistry.GRANITE);
		register(POLISHED_GRANITE);
		register(DIORITE, CustomBlockTypeRegistry.DIORITE);
		register(POLISHED_DIORITE);
		register(ANDESITE, CustomBlockTypeRegistry.ANDESITE);
		register(POLISHED_ANDESITE);

		register(SANDSTONE_TRIGGER, CustomBlockTypeRegistry.SANDSTONE_TRIGGER);
		register(SMOOTH_SANDSTONE, CustomBlockTypeRegistry.SMOOTH_SANDSTONE);

		register(STONE_AGE_CRAFTING_TABLE, CustomBlockTypeRegistry.STONE_AGE_CRAFTING_TABLE);
		register(STONE_AGE_CAMPFIRE, CustomBlockTypeRegistry.STONE_AGE_CAMPFIRE);
		register(EGYPTIAN_CAMPFIRE_STAGE1, CustomBlockTypeRegistry.EGYPTIAN_CAMPFIRE_STAGE1);

		register(SHARP_STICK);
		register(ADMIN_SWORD);
	}

	public static CustomItem getItem(String ID) {
		for (CustomItem item : items) {
			if (item.ID.equalsIgnoreCase(ID)) {
				return item;
			}
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public static CustomItem getItem(ItemStack vanillaStack) {
		ItemMeta meta = vanillaStack.getItemMeta();
		if (meta == null) {
			return null;
		}
		String name = meta.getDisplayName();
		if (name == null) {
			return null;
		}
		for (CustomItem item : items) {
			if (item.base_material == vanillaStack.getType() && item.item_name.equals(name)
					&& item.vanilla_data == vanillaStack.getData().getData()) {
				return item;
			}
		}
		return null;
	}

	public static List<CustomItem> getContents() {
		return items;
	}

}
