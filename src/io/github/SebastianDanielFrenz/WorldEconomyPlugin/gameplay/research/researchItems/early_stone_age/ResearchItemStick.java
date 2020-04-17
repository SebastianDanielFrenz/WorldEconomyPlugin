package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ExperienceResearchCondition;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchCondition;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.StatisticCategoryRegistry;

public class ResearchItemStick extends ResearchItem {

	public ResearchItemStick() {
		super("Stick", CustomItemRegistry.STICK, new ResearchItem[] {},
				new ResearchCondition[] {
						new ExperienceResearchCondition(CustomBlockTypeRegistry.TREE_OAK_LEAVES, StatisticCategoryRegistry.MINED, 10) },
				Age.EARLY_STONE_AGE);
	}

}
