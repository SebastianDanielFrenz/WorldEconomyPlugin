package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ExperienceResearchCondition;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchCondition;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.counter.LeafStickCounter;

public class ResearchItemOakDryLeaves extends ResearchItem {

	public ResearchItemOakDryLeaves() {
		super("dry_oak_leaves", CustomItemRegistry.OAK_DRY_LEAVES,
				new ResearchItem[] { ResearchItemRegistry.OAK_LEAVES },
				new ResearchCondition[] { new ExperienceResearchCondition(new LeafStickCounter(), 10) },
				Age.OLD_STONE_AGE);
	}

}
