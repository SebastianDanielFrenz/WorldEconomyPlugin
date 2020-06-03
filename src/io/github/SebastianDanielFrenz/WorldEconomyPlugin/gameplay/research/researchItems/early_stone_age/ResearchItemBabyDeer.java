package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity.CustomEntityTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchCondition;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;

public class ResearchItemBabyDeer extends ResearchItem {

	public ResearchItemBabyDeer() {
		super("baby_deer", CustomEntityTypeRegistry.BABY_DEER, new ResearchItem[] {}, new ResearchCondition[] {}, Age.EARLY_STONE_AGE, "Baby Deer",
				Material.MONSTER_EGG, 92);
	}

}
