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

public class BlockAndesite extends CustomBlock {

	public BlockAndesite() {
		super("andesite", Material.ANDESITE, true,
				new CustomBlockDropTable(
						new CustomBlockDrop[] { new CustomBlockDrop(CustomToolType.PICKAXE, CustomMaterialLevel.DIORITE,
								new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.ANDESITE, 1) }) }),
				CustomEmptyBlockData.class);
	}

}
