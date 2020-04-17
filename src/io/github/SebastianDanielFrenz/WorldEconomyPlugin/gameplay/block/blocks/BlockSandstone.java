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

public class BlockSandstone extends CustomBlockType {

	public BlockSandstone() {
		super("sandstone", Material.SANDSTONE, true, new CustomBlockDropTable(
				new CustomBlockDrop[] { new CustomBlockDrop(CustomToolType.PICKAXE, CustomMaterialLevel.ANDESITE,
						new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.SAND, 1) }) }),
				CustomEmptyBlockData.class);
	}

}
