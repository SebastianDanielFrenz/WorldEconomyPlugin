package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block;

import java.sql.SQLException;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.MetadataValue;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.event.CustomBlockDataDoesNotExistException;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomToolType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.StatisticalObject;

public abstract class CustomBlockType implements StatisticalObject {

	public CustomBlockType(String ID, Material material, boolean vanilla, CustomBlockDropTable drop_table,
			Class<? extends CustomBlockData> blockDataType) {
		this.ID = ID;
		this.material = material;
		this.drop_table = drop_table;
		this.vanilla = vanilla;
		this.blockDataType = blockDataType;
	}

	public final String ID;
	public final Material material;
	public final CustomBlockDropTable drop_table;
	public final boolean vanilla;
	public final Class<? extends CustomBlockData> blockDataType;

	public static CustomBlockType getVanillaBlock(Block block) {
		Material material = block.getType();
		for (CustomBlockType _block : CustomBlockTypeRegistry.getContents()) {
			if (_block.material == material && _block.vanilla) {
				return _block;
			}
		}
		return null;
	}

	public CustomItemStack[] getDrops(CustomToolType tool, CustomMaterialLevel tool_lvl) {
		return drop_table.getDrops(tool, tool_lvl);
	}

	public static void placeBlock(Location location, CustomBlockType block, CustomBlockData data) throws SQLException {
		if (!block.vanilla) {
			WEDB.registerCustomBlock(location, block, data);
		}
		location.getBlock().setType(block.material);
		location.getBlock().setMetadata("customBlockType", new CustomBlockMetadataValue(block, data));
	}

	public static void placeBlock(Location location, CustomBlockType block) throws SQLException, InstantiationException, IllegalAccessException {
		CustomBlockData data = block.blockDataType.newInstance();

		if (!block.vanilla) {
			WEDB.registerCustomBlock(location, block, data);
		}
		location.getBlock().setType(block.material);
		location.getBlock().setMetadata("customBlockType", new CustomBlockMetadataValue(block, data));
	}

	public static void placeBlock(Block vanillaBlock, CustomBlockType block, CustomBlockData data) throws SQLException {
		if (!block.vanilla) {
			WEDB.registerCustomBlock(vanillaBlock.getLocation(), block, data);
		}

		vanillaBlock.setType(block.material);
		vanillaBlock.setMetadata("customBlockType", new CustomBlockMetadataValue(block, data));
	}

	public static void placeBlock(Block vanillaBlock, CustomBlockType block) throws SQLException, InstantiationException, IllegalAccessException {
		CustomBlockData data = block.blockDataType.newInstance();

		if (!block.vanilla) {
			WEDB.registerCustomBlock(vanillaBlock.getLocation(), block, data);
		}
		vanillaBlock.setType(block.material);
		vanillaBlock.setMetadata("customBlockType", new CustomBlockMetadataValue(block, data));
	}

	public void onPlayerInteractEvent(PlayerInteractEvent event) {
	}

	@Override
	public String getStatisticID() {
		return "block_" + ID;
	}

	public static CustomBlockMetadataValue getMetadata(Block block) {
		List<MetadataValue> metadata_values = block.getMetadata("customBlockType");
		if (metadata_values.size() == 0) {
			throw new CustomBlockDataDoesNotExistException(block);
		} else {
			return (CustomBlockMetadataValue) metadata_values.get(0);
		}
	}

	public static CustomBlockDropTable easyDrop(CustomItem item) {
		return new CustomBlockDropTable(new CustomBlockDrop(CustomToolType.ALL, CustomMaterialLevel.HAND, new CustomItemStack(item, 1)));
	}

}
