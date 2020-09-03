package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.CustomBlockDataCreationException;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric.PowerGrid;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric.PowerGridMemberType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric.PowerGridRegistry;

/**
 * The power grid data doesn't need to be saved as the constructors of this
 * class will recreate the power grid.
 * 
 * @author crash
 *
 */
public abstract class PowerConnectedBlockData extends CustomBlockData {

	public PowerConnectedBlockData(Location location) {
		super(location);

		powerGrid = PowerGridRegistry.extendPowerGridTo(location, getPowerGridMemberType());
	}

	public PowerConnectedBlockData(Location location, String rawData) throws CustomBlockDataCreationException {
		super(location, rawData);

		powerGrid = PowerGridRegistry.extendPowerGridTo(location, getPowerGridMemberType());
	}

	public PowerGrid powerGrid;

	public abstract PowerGridMemberType getPowerGridMemberType();

	public abstract int getPriority();

}
