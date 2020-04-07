package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block;

import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomToolType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.statistics.CustomStatisticObject;

public abstract class CustomBlock implements CustomStatisticObject {

	public CustomBlock(String ID, Material material, boolean vanilla, CustomBlockDropTable drop_table,
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

	public static CustomBlock getVanillaBlock(Block block) {
		Material material = block.getType();
		for (CustomBlock _block : CustomBlockRegistry.getContents()) {
			if (_block.material == material && _block.vanilla) {
				return _block;
			}
		}
		return null;
	}

	public CustomItemStack[] getDrops(CustomToolType tool, CustomMaterialLevel tool_lvl) {
		return drop_table.getDrops(tool, tool_lvl);
	}

	public static void placeBlock(Location location, CustomBlock block, CustomBlockData data) throws SQLException {
		WEDB.registerCustomBlock(location, block, data);
		location.getBlock().setType(block.material);
		location.getBlock().setMetadata("customBlockType", new CustomBlockMetadataValue(block, data));
	}

	public static void placeBlock(Location location, CustomBlock block)
			throws SQLException, InstantiationException, IllegalAccessException {
		CustomBlockData data = block.blockDataType.newInstance();

		WEDB.registerCustomBlock(location, block, data);
		location.getBlock().setType(block.material);
		location.getBlock().setMetadata("customBlockType", new CustomBlockMetadataValue(block, data));
	}

	public static void placeBlock(Block vanillaBlock, CustomBlock block, CustomBlockData data) throws SQLException {
		WEDB.registerCustomBlock(vanillaBlock.getLocation(), block, data);
		vanillaBlock.setType(block.material);
		vanillaBlock.setMetadata("customBlockType", new CustomBlockMetadataValue(block, data));
	}

	public static void placeBlock(Block vanillaBlock, CustomBlock block)
			throws SQLException, InstantiationException, IllegalAccessException {
		CustomBlockData data = block.blockDataType.newInstance();

		WEDB.registerCustomBlock(vanillaBlock.getLocation(), block, data);
		vanillaBlock.setType(block.material);
		vanillaBlock.setMetadata("customBlockType", new CustomBlockMetadataValue(block, data));
	}

}
