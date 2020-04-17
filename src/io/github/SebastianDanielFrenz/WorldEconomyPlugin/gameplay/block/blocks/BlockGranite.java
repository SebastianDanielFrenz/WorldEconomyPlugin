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

public class BlockGranite extends CustomBlockType {

	public BlockGranite() {
		super("granite", Material.GRANITE, true,
				new CustomBlockDropTable(new CustomBlockDrop[] {
						new CustomBlockDrop(CustomToolType.PICKAXE, CustomMaterialLevel.PROCESSED_COBBLESTONE,
								new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.GRANITE, 1) }) }),
				CustomEmptyBlockData.class);
	}

}
