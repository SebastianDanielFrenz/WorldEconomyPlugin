package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research;

import java.util.ArrayList;
import java.util.List;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age.ResearchItemBarries;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age.ResearchItemOakLeaves;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age.ResearchItemOakPlanks;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age.ResearchItemStick;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age.ResearchItemTreeOakLeaves;

public class ResearchItemRegistry {

	private static List<ResearchItem> researchItems = new ArrayList<ResearchItem>();

	public static void register(ResearchItem researchItem) {
		researchItems.add(researchItem);
	}

	public static final ResearchItem STICK = new ResearchItemStick();
	public static final ResearchItem BARRIES = new ResearchItemBarries();
	public static final ResearchItem OAK_LEAVES = new ResearchItemOakLeaves();
	public static final ResearchItem TREE_OAK_LEAVES = new ResearchItemTreeOakLeaves();
	
	public static final ResearchItem OAK_PLANKS = new ResearchItemOakPlanks();

	public static void init() {
		register(BARRIES);
		register(OAK_LEAVES);
		register(TREE_OAK_LEAVES);

		register(STICK);
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
