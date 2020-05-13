package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchCondition;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;

public class ResearchItemOakLeaves extends ResearchItem {

	public ResearchItemOakLeaves() {
		super("leaves", CustomItemRegistry.OAK_LEAVES, new ResearchItem[] {}, new ResearchCondition[] {}, Age.EARLY_STONE_AGE);
	}

}
