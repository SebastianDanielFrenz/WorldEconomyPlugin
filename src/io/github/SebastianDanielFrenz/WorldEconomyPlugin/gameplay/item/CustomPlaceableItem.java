package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;

public abstract class CustomPlaceableItem extends CustomItem {

	public CustomPlaceableItem(String ID, Material base, Age age, String name, ItemCategory category, boolean vanilla) {
		super(ID, base, age, name, category, vanilla);
	}

	public CustomPlaceableItem(String ID, Material base, Age age, String name, ItemCategory category,
			ItemDetail[] details, boolean vanilla) {
		super(ID, base, age, name, category, details, vanilla);
	}

	public CustomPlaceableItem(String ID, Material base, Age age, String name, ItemCategory category) {
		super(ID, base, age, name, category);
	}

	public CustomPlaceableItem(String ID, Material base, Age age, String name, ItemCategory category,
			ItemDetail[] details) {
		super(ID, base, age, name, category, details);
	}

	// now with data values

	public CustomPlaceableItem(String ID, Material base, int data, Age age, String name, ItemCategory category,
			boolean vanilla) {
		super(ID, base, data, age, name, category, vanilla);
	}

	public CustomPlaceableItem(String ID, Material base, int data, Age age, String name, ItemCategory category,
			ItemDetail[] details, boolean vanilla) {
		super(ID, base, data, age, name, category, details, vanilla);
	}

	public CustomPlaceableItem(String ID, Material base, int data, Age age, String name, ItemCategory category) {
		super(ID, base, data, age, name, category);
	}

	public CustomPlaceableItem(String ID, Material base, int data, Age age, String name, ItemCategory category,
			ItemDetail[] details) {
		super(ID, base, data, age, name, category, details);
	}

	private CustomBlockType block;

	public void setBlock(CustomBlockType block) {
		this.block = block;
	}

	public CustomBlockType getBlock() {
		return block;
	}

	@Override
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			System.out.println(block);

			BlockFace face = event.getBlockFace();
			try {
				Block _block = event.getClickedBlock().getRelative(face);
				CustomBlockData data = block.blockDataType.getConstructor(Location.class)
						.newInstance(_block.getLocation());

				event.setCancelled(true);
				CustomBlockType.placeBlock(_block, block, data);
				// event.setCancelled(true);
				ItemStack hand = event.getPlayer().getInventory().getItemInMainHand();

				if (hand.getAmount() == 1) {
					event.getPlayer().getInventory().setItemInMainHand(null);
				} else {
					hand.setAmount(hand.getAmount() - 1);
				}
			} catch (InstantiationException | IllegalAccessException | SQLException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
