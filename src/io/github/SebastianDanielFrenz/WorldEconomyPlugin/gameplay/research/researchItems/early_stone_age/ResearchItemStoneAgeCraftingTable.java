package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ExperienceResearchCondition;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchCondition;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchableObject;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.StatisticCategoryRegistry;

public class ResearchItemStoneAgeCraftingTable extends ResearchItem {

	public ResearchItemStoneAgeCraftingTable(String ID, ResearchableObject researchableObject, ResearchItem[] parents, ResearchCondition[] conditions,
			Age age) {
		super("stone_age_crafting_table", CustomItemRegistry.STONE_AGE_CRAFTING_TABLE, new ResearchItem[] { ResearchItemRegistry.OAK_PLANKS },
				new ResearchCondition[] {
						new ExperienceResearchCondition(CustomBlockTypeRegistry.TREE_OAK_LEAVES, StatisticCategoryRegistry.MINED, 400) },
				Age.EARLY_STONE_AGE);
	}

}
