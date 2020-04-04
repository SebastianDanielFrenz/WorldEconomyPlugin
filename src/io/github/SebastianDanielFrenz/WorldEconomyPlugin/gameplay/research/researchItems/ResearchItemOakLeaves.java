package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchCondition;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;

public class ResearchItemOakLeaves extends ResearchItem {

	public ResearchItemOakLeaves() {
		super("Leaves", CustomItemRegistry.OAK_LEAVES, new ResearchItem[] {}, new ResearchCondition[] {}, Age.OLD_STONE_AGE);
	}

}
