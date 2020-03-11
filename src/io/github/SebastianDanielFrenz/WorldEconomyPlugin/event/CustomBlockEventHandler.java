package io.github.SebastianDanielFrenz.WorldEconomyPlugin.event;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomBlock;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.ItemDetailType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.ToolItemDetail;

public class CustomBlockEventHandler implements Listener {

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
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
			customBlock = CustomBlock.valueOf(metadata_values.get(0).asString());
		}

		@SuppressWarnings("deprecation")
		ItemStack hand = player.getInventory().getItemInHand();
		if (hand == null) {
			return;
		}
		if (!hand.hasItemMeta()) {
			return;
		}
		CustomItem item = CustomItem.getItem(hand);
		ToolItemDetail toolDetails = (ToolItemDetail) item.getDetail(ItemDetailType.TOOL);

		CustomItemStack[] drops = customBlock.getDrops(toolDetails.getToolType(), toolDetails.getToolLevel());
		ItemStack[] usable_drops = CustomItemStack.convert(drops);

		for (ItemStack usable_drop : usable_drops) {
			block.getWorld().dropItemNaturally(block.getLocation(), usable_drop);
		}

		event.setCancelled(true);
		block.setType(Material.AIR);
	}

}
