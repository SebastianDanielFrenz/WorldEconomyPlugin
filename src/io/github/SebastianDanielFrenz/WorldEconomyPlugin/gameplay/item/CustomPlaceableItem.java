package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item;

import java.sql.SQLException;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlock;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;

public class CustomPlaceableItem extends CustomItem {

	public CustomPlaceableItem(String ID, Material base, Tier tier, String name, ItemCategory category,
			boolean vanilla) {
		super(ID, base, tier, name, category, vanilla);
	}

	public CustomPlaceableItem(String ID, Material base, Tier tier, String name, ItemCategory category,
			ItemDetail[] details, boolean vanilla) {
		super(ID, base, tier, name, category, details, vanilla);
	}

	public CustomPlaceableItem(String ID, Material base, Tier tier, String name, ItemCategory category) {
		super(ID, base, tier, name, category);
	}

	public CustomPlaceableItem(String ID, Material base, Tier tier, String name, ItemCategory category,
			ItemDetail[] details) {
		super(ID, base, tier, name, category, details);
	}

	private CustomBlock block;

	public void setBlock(CustomBlock block) {
		this.block = block;
	}

	public CustomBlock getBlock() {
		return block;
	}

	@Override
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			System.out.println(block);

			BlockFace face = event.getBlockFace();
			try {
				Block _block = event.getClickedBlock().getRelative(face);
				CustomBlockData data = block.blockDataType.newInstance();

				CustomBlock.placeBlock(_block, block, data);
				event.setCancelled(true);
				ItemStack hand = event.getPlayer().getInventory().getItemInMainHand();
				if (hand.getAmount() == 1) {
					event.getPlayer().getInventory().setItemInMainHand(null);
				} else {
					hand.setAmount(hand.getAmount() - 1);
				}
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
