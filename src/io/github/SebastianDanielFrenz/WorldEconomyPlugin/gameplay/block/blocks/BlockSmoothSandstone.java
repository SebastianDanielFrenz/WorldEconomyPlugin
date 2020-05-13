package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.CustomEmptyBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDrop;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDropTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomToolType;

public class BlockSmoothSandstone extends CustomBlockType {

	public BlockSmoothSandstone() {
		super("smooth_sandstone", Material.SANDSTONE, 2, true,
				new CustomBlockDropTable(new CustomBlockDrop[] { new CustomBlockDrop(CustomToolType.PICKAXE,
						CustomMaterialLevel.ANDESITE,
						new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.SMOOTH_SANDSTONE, 1) }) }),
				CustomEmptyBlockData.class);
	}

}
