package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.undefined;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ImpossibleResearchCondition;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;

public class ImpossibleResearchItem extends ResearchItem {

	public ImpossibleResearchItem() {
		super("impossible_research_item", null, new ResearchItem[] {}, new ImpossibleResearchCondition(), Age.UNDEFINED,
				"Impossible Research Item", Material.BARRIER, 0);
	}

}
