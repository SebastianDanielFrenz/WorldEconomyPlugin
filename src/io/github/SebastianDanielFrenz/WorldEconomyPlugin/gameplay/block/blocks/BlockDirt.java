package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.EmptyCustomBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;

public class BlockDirt extends CustomBlockType {

	public BlockDirt() {
		super(WorldEconomyPlugin.plugin, "dirt", Material.DIRT, true, easyDrop(CustomItemRegistry.DIRT),
				EmptyCustomBlockData.class);
	}

}
