package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric;

import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.PowerConnectedBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDropTable;

public class PowerConnectedBlockType extends CustomBlockType {

	public PowerConnectedBlockType(Plugin plugin, String ID, Material material, int data, boolean vanilla, CustomBlockDropTable drop_table,
			Class<? extends PowerConnectedBlockData> blockDataType) {
		super(plugin, ID, material, data, vanilla, drop_table, blockDataType);
	}

}
