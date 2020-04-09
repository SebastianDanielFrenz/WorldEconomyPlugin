package io.github.SebastianDanielFrenz.WorldEconomyPlugin.event;

import java.sql.SQLException;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlock;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockMetadataValue;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomToolType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemDetailType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ToolItemDetail;

public class CustomBlockEventHandler implements Listener {

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) throws SQLException {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		List<MetadataValue> metadata_values = block.getMetadata("customBlockType");

		CustomBlock customBlock;

		if (metadata_values.size() == 0) {
			customBlock = CustomBlock.getVanillaBlock(block);
			if (customBlock == null) {
				return;
			}
		} else {
			customBlock = ((CustomBlockMetadataValue) metadata_values.get(0)).getBlock();
		}

		if (player.getGameMode() != GameMode.CREATIVE) {
			@SuppressWarnings("deprecation")
			ItemStack hand = player.getInventory().getItemInHand();
			ToolItemDetail toolDetails;

			if (hand.getType() == Material.AIR) {
				toolDetails = new ToolItemDetail(CustomToolType.HAND, CustomMaterialLevel.HAND);
			} else if (hand.getItemMeta().getDisplayName().equals("")) {
				CustomItem item = CustomItem.getItem(hand);
				if (item == null) {
					event.setCancelled(true);
					return;
				}
				toolDetails = (ToolItemDetail) item.getDetail(ItemDetailType.TOOL);
			} else {
				CustomItem item = CustomItem.getItem(hand);
				toolDetails = (ToolItemDetail) item.getDetail(ItemDetailType.TOOL);
				if (toolDetails == null) {
					toolDetails = new ToolItemDetail(CustomToolType.HAND, CustomMaterialLevel.HAND);
				}
			}

			CustomItemStack[] drops = customBlock.getDrops(toolDetails.getToolType(), toolDetails.getToolLevel());
			if (drops.length == 0) {
				// if no drops are found, you are not allowed to mine the block.
				event.setCancelled(true);
				return;
			}

			ItemStack[] usable_drops = CustomItemStack.convert(drops);

			for (ItemStack usable_drop : usable_drops) {
				if (usable_drop != null) {
					block.getWorld().dropItemNaturally(block.getLocation(), usable_drop);
				}
			}
		}

		event.setCancelled(true);
		block.setType(Material.AIR);
		WEDB.removeCustomBlock(event.getBlock().getLocation());
	}

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();
		if (block == null) {
			return;
		}
		List<MetadataValue> metadata_values = block.getMetadata("customBlockType");

		CustomBlock customBlock;

		if (metadata_values.size() == 0) {
			customBlock = CustomBlock.getVanillaBlock(block);
			if (customBlock == null) {
				return;
			}
		} else {
			customBlock = ((CustomBlockMetadataValue) metadata_values.get(0)).getBlock();
		}

		customBlock.onPlayerInteractEvent(event);
	}

}
