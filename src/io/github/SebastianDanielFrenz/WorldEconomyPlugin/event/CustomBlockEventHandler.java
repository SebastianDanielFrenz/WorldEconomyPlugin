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
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockMetadataValue;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomToolType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ToolItemDetail;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.StatisticCategoryRegistry;

public class CustomBlockEventHandler implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) throws SQLException {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		List<MetadataValue> metadata_values = block.getMetadata("customBlockType");

		CustomBlockType customBlock;

		if (metadata_values.size() == 0) {
			customBlock = CustomBlockType.getVanillaBlock(block);
			if (customBlock == null) {
				System.out.println(
						"broke a truly vanilla block (material=" + block.getType() + ", data=" + block.getData() + "!");
				return;
			}
			System.out.println("broke custom vanilla block " + customBlock.ID);
		} else {
			customBlock = ((CustomBlockMetadataValue) metadata_values.get(0)).getBlock();
			System.out.println("broke custom block " + customBlock.ID);
		}

		if (player.getGameMode() != GameMode.CREATIVE) {
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
				toolDetails = (ToolItemDetail) item.getDetail(ToolItemDetail.class);
			} else {
				CustomItem item = CustomItem.getItem(hand);
				toolDetails = (ToolItemDetail) item.getDetail(ToolItemDetail.class);
				if (toolDetails == null) {
					toolDetails = new ToolItemDetail(CustomToolType.HAND, CustomMaterialLevel.HAND);
				}
			}

			List<CustomItemStack> drops = customBlock.getDrops(toolDetails.getToolType(), toolDetails.getToolLevel());
			if (drops.size() == 0) {
				// if no drops are found, you are not allowed to mine the block.
				event.setCancelled(true);
				return;
			}

			int i = 0;
			while (i < drops.size()) {
				if (drops.get(i).getCount() == 0) {
					drops.remove(i);
					continue;
				}
				i++;
			}

			if (drops.size() == 0) {
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

		WEDB.incrementStatistic(customBlock, StatisticCategoryRegistry.MINED, WEDB.getUserProfile(player).ID, "player",
				1);
	}

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();
		if (block == null) {
			return;
		}
		List<MetadataValue> metadata_values = block.getMetadata("customBlockType");

		CustomBlockType customBlock;

		if (metadata_values.size() == 0) {
			customBlock = CustomBlockType.getVanillaBlock(block);
			if (customBlock == null) {
				return;
			}
		} else {
			customBlock = ((CustomBlockMetadataValue) metadata_values.get(0)).getBlock();
		}

		customBlock.onPlayerInteractEvent(event);
	}

}
