package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlock;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockDrop;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockDropTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.CustomEmptyBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomToolType;

public class BlockEgyptianCampfireStage1 extends CustomBlock {

	public BlockEgyptianCampfireStage1() {
		super("egyptian_campfire_stage1", Material.CAMPFIRE, false,
				new CustomBlockDropTable(
						new CustomBlockDrop[] { new CustomBlockDrop(CustomToolType.ALL, CustomMaterialLevel.HAND,
								new CustomItemStack[] {
										new CustomItemStack(CustomItemRegistry.EGYPTIAN_CAMPFIRE_STAGE1, 1) }) }),
				CustomEmptyBlockData.class);
	}

}
