package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block;

import org.bukkit.Material;
import org.bukkit.block.Block;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomBlockDropTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomToolType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;

public abstract class CustomBlock {

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

}
