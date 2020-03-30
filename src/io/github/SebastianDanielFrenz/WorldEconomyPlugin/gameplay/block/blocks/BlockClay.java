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

public class BlockClay extends CustomBlock {

	public BlockClay() {
		super("clay", Material.CLAY, true, new CustomBlockDropTable(
				new CustomBlockDrop[] { new CustomBlockDrop(CustomToolType.SHOVEL, CustomMaterialLevel.COBBLESTONE,
						new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.CLAY_BALL, 1) }) }),
				CustomEmptyBlockData.class);
	}

}
