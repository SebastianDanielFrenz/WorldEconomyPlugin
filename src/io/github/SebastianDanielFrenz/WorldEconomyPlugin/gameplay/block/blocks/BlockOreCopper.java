package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomBlockDrop;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomBlockDropTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomToolType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlock;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.CustomEmptyBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;

public class BlockOreCopper extends CustomBlock {

	public BlockOreCopper() {
		super("copper_ore", Material.IRON_ORE, false, new CustomBlockDropTable(
				new CustomBlockDrop[] { new CustomBlockDrop(CustomToolType.PICKAXE, CustomMaterialLevel.ANDESITE,
						new CustomItemStack[] { new CustomItemStack(CustomItem.COPPER_ORE, 1) }) }),
				CustomEmptyBlockData.class);
	}

}
