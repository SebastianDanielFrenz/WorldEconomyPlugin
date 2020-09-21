package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDropTable;

public abstract class PowerConsumerBlockType extends CustomBlockType {

	public PowerConsumerBlockType(Plugin plugin, String ID, Material material, int data, boolean vanilla, CustomBlockDropTable drop_table,
			Class<? extends CustomBlockData> blockDataType) {
		super(plugin, ID, material, data, vanilla, drop_table, blockDataType);
	}

	/**
	 * Returns the max amount of power that can be accepted by the block in this
	 * tick.
	 * 
	 * @param location
	 * @param blockData
	 * @return
	 */
	public abstract double getMaxPower(Location location, CustomBlockData blockData);

	/**
	 * Returns true, if the given amount of power can be used by the
	 * machine.<br>
	 * <br>
	 * This can be used to enforce a min power.
	 * 
	 * @param location
	 * @param blockData
	 * @param amount
	 * @return
	 */
	public abstract boolean acceptPower(Location location, CustomBlockData blockData, double amount);

	/**
	 * Saves the amount of power for later processing after finishing the power
	 * distribution. This method can be called multiple times in one tick.
	 * 
	 * @param location
	 * @param blockData
	 * @param amount
	 */
	public abstract void assignPower(Location location, CustomBlockData blockData, double amount);

	/**
	 * Uses the assigned power. This should enclude the machine's working
	 * process and resetting the temporary power storage ussed to track the
	 * assigned power.
	 * 
	 * @param location
	 * @param blockData
	 */
	public abstract void usePower(Location location, CustomBlockData blockData);

}
