package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomBlockDrop;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomBlockDropTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomToolType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlock;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.CustomEmptyBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;

public class BlockSand extends CustomBlock {

	public BlockSand() {
		super("sand", Material.SAND, true,
				new CustomBlockDropTable(
						new CustomBlockDrop[] { new CustomBlockDrop(CustomToolType.SHOVEL, CustomMaterialLevel.WOOD,
								new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.SAND, 1) }) }),
				CustomEmptyBlockData.class);
	}

}
