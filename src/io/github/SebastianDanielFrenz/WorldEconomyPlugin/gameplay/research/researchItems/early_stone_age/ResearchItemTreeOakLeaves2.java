package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.early_stone_age;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchCondition;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;

public class ResearchItemTreeOakLeaves2 extends ResearchItem {

	public ResearchItemTreeOakLeaves2() {
		super("tree_oak_leaves2", CustomBlockTypeRegistry.TREE_OAK_LEAVES2, new ResearchItem[] {},
				new ResearchCondition[] {}, Age.EARLY_STONE_AGE, "Tree Oak Leaves", Material.LEAVES, 0);
	}

}
