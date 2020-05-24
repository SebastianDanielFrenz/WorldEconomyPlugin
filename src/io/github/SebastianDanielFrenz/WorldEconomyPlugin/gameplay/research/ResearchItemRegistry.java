package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research;

import java.util.ArrayList;
import java.util.List;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age.ResearchItemBabyDeer;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age.ResearchItemOakLeaves;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age.ResearchItemOakPlanks;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age.ResearchItemSharpStick;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age.ResearchItemStick;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age.ResearchItemStoneAgeCraftingTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age.ResearchItemTreeOakLeaves;

public class ResearchItemRegistry {

	private static List<ResearchItem> researchItems = new ArrayList<ResearchItem>();

	public static void register(ResearchItem researchItem) {
		researchItems.add(researchItem);
	}

	public static final ResearchItem STICK = new ResearchItemStick();
	public static final ResearchItem OAK_LEAVES = new ResearchItemOakLeaves();
	public static final ResearchItem TREE_OAK_LEAVES = new ResearchItemTreeOakLeaves();
	public static final ResearchItem BABY_DEER = new ResearchItemBabyDeer();

	public static final ResearchItem OAK_PLANKS = new ResearchItemOakPlanks();
	public static final ResearchItem STONE_AGE_CRAFTING_TABLE = new ResearchItemStoneAgeCraftingTable();
	public static final ResearchItem SHARP_STICK = new ResearchItemSharpStick();

	public static void init() {
		register(OAK_LEAVES);
		register(TREE_OAK_LEAVES);
		register(STICK);
		register(BABY_DEER);

		register(OAK_PLANKS);
		register(STONE_AGE_CRAFTING_TABLE);
		register(SHARP_STICK);
	}

	public static List<ResearchItem> getContents() {
		return researchItems;
	}

	public static ResearchItem get(String ID) {
		for (ResearchItem item : researchItems) {
			if (item.getID().equals(ID)) {
				return item;
			}
		}
		return null;
	}

}
