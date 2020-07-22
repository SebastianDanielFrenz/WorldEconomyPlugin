package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.EmptyCustomBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDrop;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDropTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomToolType;

public class BlockSandstoneTrigger extends CustomBlockType {

	public BlockSandstoneTrigger() {
		super(WorldEconomyPlugin.plugin, "sandstone_trigger", Material.SANDSTONE, 1, false,
				new CustomBlockDropTable(new CustomBlockDrop[] { new CustomBlockDrop(CustomToolType.PICKAXE,
						CustomMaterialLevel.ANDESITE,
						new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.SANDSTONE_TRIGGER, 1) }) }),
				EmptyCustomBlockData.class);
	}

	@Override
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		event.getPlayer().sendMessage("click!");
	}

}
