package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchCondition;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;

public class ResearchItemTreeOakLeaves extends ResearchItem {

	public ResearchItemTreeOakLeaves() {
		super("tree_oak_leaves", CustomBlockTypeRegistry.TREE_OAK_LEAVES, new ResearchItem[] {}, new ResearchCondition[] {}, Age.EARLY_STONE_AGE,
				"Tree Oak Leaves", Material.LEAVES, 0);
	}

}
