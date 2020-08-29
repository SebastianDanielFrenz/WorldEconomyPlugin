package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDropTable;

public abstract class PowerConsumerBlockType extends CustomBlockType {

	public PowerConsumerBlockType(Plugin plugin, String ID, Material material, int data, boolean vanilla,
			CustomBlockDropTable drop_table, Class<? extends CustomBlockData> blockDataType) {
		super(plugin, ID, material, data, vanilla, drop_table, blockDataType);
	}

	public abstract double getPowerRequested(Location location, CustomBlockData blockData);

}
