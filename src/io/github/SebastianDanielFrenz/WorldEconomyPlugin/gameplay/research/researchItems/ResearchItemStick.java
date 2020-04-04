package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchCondition;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;

public class ResearchItemStick extends ResearchItem {

	public ResearchItemStick() {
		super("Stick", CustomItemRegistry.STICK, new ResearchItem[] {}, new ResearchCondition[] {}, Age.OLD_STONE_AGE);
	}

}
