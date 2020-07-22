package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.EmptyCustomBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDrop;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDropChanceComponent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDropComponent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDropDefaultComponent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDropTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomToolType;

public abstract class BlockTreeOakLeaves extends CustomBlockType {

	public BlockTreeOakLeaves(String ID, int repr_dmg) {
		super(WorldEconomyPlugin.plugin, ID, Material.LEAVES, repr_dmg, true,
				new CustomBlockDropTable(new CustomBlockDrop[] {
						new CustomBlockDrop(CustomToolType.HAND, CustomMaterialLevel.HAND,
								new CustomBlockDropComponent[] {
										new CustomBlockDropChanceComponent(
												new CustomItemStack(CustomItemRegistry.OAK_LEAVES, 1), 0.5),
										new CustomBlockDropChanceComponent(
												new CustomItemStack(CustomItemRegistry.STICK, 1), 0.2) }),
						new CustomBlockDrop(CustomToolType.SHEERS, CustomMaterialLevel.WOOD,
								new CustomBlockDropComponent[] {
										new CustomBlockDropDefaultComponent(new CustomItemStack(
												CustomItemRegistry.OAK_LEAVES, 1)),
										new CustomBlockDropChanceComponent(
												new CustomItemStack(CustomItemRegistry.STICK, 1), 0.2) }),
						new CustomBlockDrop(CustomToolType.ALL, CustomMaterialLevel.HAND,
								new CustomBlockDropEmptyComponent()) }),
				EmptyCustomBlockData.class);
	}

}