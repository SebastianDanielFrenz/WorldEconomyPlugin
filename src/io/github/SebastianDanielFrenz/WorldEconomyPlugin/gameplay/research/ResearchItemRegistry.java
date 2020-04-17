package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research;

import java.util.ArrayList;
import java.util.List;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age.ResearchItemBarries;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age.ResearchItemOakDryLeaves;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age.ResearchItemOakLeaves;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age.ResearchItemStick;

public class ResearchItemRegistry {

	private static List<ResearchItem> researchItems = new ArrayList<ResearchItem>();

	public static void register(ResearchItem researchItem) {
		researchItems.add(researchItem);
	}

	public static final ResearchItem STICK = new ResearchItemStick();
	public static final ResearchItem BARRIES = new ResearchItemBarries();
	public static final ResearchItem OAK_LEAVES = new ResearchItemOakLeaves();

	public static final ResearchItem OAK_DRY_LEAVES = new ResearchItemOakDryLeaves();

	public static void init() {
		register(STICK);
		register(BARRIES);
		register(OAK_LEAVES);

		register(OAK_DRY_LEAVES);
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
