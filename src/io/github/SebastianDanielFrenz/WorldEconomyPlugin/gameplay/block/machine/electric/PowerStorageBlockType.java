package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.PowerStorageBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDropTable;

public abstract class PowerStorageBlockType extends CustomBlockType {

	public PowerStorageBlockType(Plugin plugin, String ID, Material material, int data, boolean vanilla, CustomBlockDropTable drop_table,
			Class<? extends PowerStorageBlockData> blockDataType) {
		super(plugin, ID, material, data, vanilla, drop_table, blockDataType);
	}

	public abstract double getCapacity();

	/**
	 * This method needs to factor in that an empty storage unit only supplies
	 * 0W.
	 * 
	 * @param location
	 * @param blockData
	 * @return
	 */
	public abstract double getMaxPowerOutput(Location location, CustomBlockData blockData);

}
