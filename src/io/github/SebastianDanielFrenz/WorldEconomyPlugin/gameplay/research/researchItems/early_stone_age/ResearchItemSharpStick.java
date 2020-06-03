package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchCondition;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItemRegistry;

public class ResearchItemSharpStick extends ResearchItem {

	public ResearchItemSharpStick() {
		super("sharp_stick", CustomItemRegistry.SHARP_STICK, new ResearchItem[] { ResearchItemRegistry.STONE_AGE_CRAFTING_TABLE },
				new ResearchCondition[] {}, Age.EARLY_STONE_AGE, "Sharp Stick", Material.STICK, 0);
	}

}
