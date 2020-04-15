package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockDrop;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockDropTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomOre;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.CustomEmptyBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomToolType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCopperOre;

public class BlockCopperOre extends CustomOre {

	public BlockCopperOre() {
		super("copper_ore",
				Material.IRON_ORE, false, new CustomBlockDropTable(new CustomBlockDrop[] { new CustomBlockDrop(CustomToolType.PICKAXE,
						CustomMaterialLevel.ANDESITE, new CustomItemStack[] { new CustomItemStack(new ItemCopperOre(), 1) }) }),
				CustomEmptyBlockData.class);
	}

	@Override
	public double getProcessedSpawnRate(int height) {
		return getChanceInterpolated(45, 50, 60, 70, getAdjustedSpawnRate(), height);
	}

	@Override
	public int getSpawnAmount(int height) {
		return 0;
	}

	@Override
	public double getRawSpawnRate() {
		return 8;
	}

}
