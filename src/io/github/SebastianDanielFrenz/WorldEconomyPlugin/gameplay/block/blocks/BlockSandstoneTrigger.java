package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.player.PlayerInteractEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockDrop;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockDropTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.CustomEmptyBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomToolType;

public class BlockSandstoneTrigger extends CustomBlockType {

	public BlockSandstoneTrigger() {
		super("sandstone_trigger", Material.CHISELED_SANDSTONE, false,
				new CustomBlockDropTable(new CustomBlockDrop[] { new CustomBlockDrop(CustomToolType.PICKAXE,
						CustomMaterialLevel.ANDESITE,
						new CustomItemStack[] { new CustomItemStack(CustomItemRegistry.SANDSTONE_TRIGGER, 1) }) }),
				CustomEmptyBlockData.class);
	}

	@Override
	public void onPlayerInteractEvent(PlayerInteractEvent event) {

		Block interacted = event.getClickedBlock();
		// Check if this is the block you want

		BlockData blockData = interacted.getRelative(BlockFace.UP, 1).getBlockData();

		if (blockData instanceof AnaloguePowerable) {
			event.getPlayer().sendMessage(":" + ((AnaloguePowerable) blockData).getPower());
			((AnaloguePowerable) blockData).setPower(5);
			event.getPlayer().sendMessage("Powering... (" + ((AnaloguePowerable) blockData).getPower());
		}
		blockData = interacted.getRelative(BlockFace.DOWN, 1).getBlockData();
		if (blockData instanceof AnaloguePowerable) {
			((AnaloguePowerable) blockData).setPower(15);
			event.getPlayer().sendMessage("Powering... (");
		}
		blockData = interacted.getRelative(BlockFace.EAST, 1).getBlockData();
		if (blockData instanceof AnaloguePowerable) {
			((AnaloguePowerable) blockData).setPower(15);
			event.getPlayer().sendMessage("Powering... (");
		}
		blockData = interacted.getRelative(BlockFace.WEST, 1).getBlockData();
		if (blockData instanceof AnaloguePowerable) {
			((AnaloguePowerable) blockData).setPower(15);
			event.getPlayer().sendMessage("Powering... (");
		}
		blockData = interacted.getRelative(BlockFace.NORTH, 1).getBlockData();
		if (blockData instanceof AnaloguePowerable) {
			((AnaloguePowerable) blockData).setPower(15);
			event.getPlayer().sendMessage("Powering... (");
		}
		blockData = interacted.getRelative(BlockFace.SOUTH, 1).getBlockData();
		if (blockData instanceof AnaloguePowerable) {
			((AnaloguePowerable) blockData).setPower(15);
			event.getPlayer().sendMessage("Powering... (");
		}
	}

}
