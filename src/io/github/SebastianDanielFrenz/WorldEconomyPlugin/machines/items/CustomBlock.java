package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.CustomBlockDrop;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.CustomBlockDropTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.CustomToolType;
import org.bukkit.Material;
import org.bukkit.block.Block;

public enum CustomBlock {

	COARSE_DIRT(Material.COARSE_DIRT, true, new CustomBlockDropTable(new CustomBlockDrop[] { new CustomBlockDrop(CustomToolType.SHOVEL,
			CustomMaterialLevel.WOOD, new CustomItemStack[] { new CustomItemStack(CustomItem.COARSE_DIRT, 1) }) })),
	SAND(Material.SAND, true, new CustomBlockDropTable(new CustomBlockDrop[] { new CustomBlockDrop(CustomToolType.SHOVEL, CustomMaterialLevel.WOOD,
			new CustomItemStack[] { new CustomItemStack(CustomItem.SAND, 1) }) })),
	CLAY(Material.CLAY, true, new CustomBlockDropTable(new CustomBlockDrop[] { new CustomBlockDrop(CustomToolType.SHOVEL,
			CustomMaterialLevel.COBBLESTONE, new CustomItemStack[] { new CustomItemStack(CustomItem.CLAY_BALL, 1) }) }));

	private CustomBlock(Material material, boolean vanilla, CustomBlockDropTable drop_table) {
		this.material = material;
		this.drop_table = drop_table;
		this.vanilla = vanilla;
	}

	public final Material material;
	public final CustomBlockDropTable drop_table;
	public final boolean vanilla;

	public static CustomBlock getVanillaBlock(Block block) {
		Material material = block.getType();
		for (CustomBlock _block : values()) {
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
