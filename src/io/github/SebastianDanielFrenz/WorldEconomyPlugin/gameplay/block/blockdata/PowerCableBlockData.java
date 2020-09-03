package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.CustomBlockDataCreationException;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric.PowerGridMemberType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric.PowerGridRegistry;

public class PowerCableBlockData extends PowerConnectedBlockData {

	public PowerCableBlockData(Location location) {
		super(location);

		powerGrid = PowerGridRegistry.extendPowerGridTo(location, PowerGridMemberType.CABLE);
		PowerGridRegistry.lastPowerGridID++;
	}

	public PowerCableBlockData(Location location, String rawData) throws CustomBlockDataCreationException {
		super(location, rawData);

		powerGrid = PowerGridRegistry.getPowerGrid(Long.parseLong(rawData)); // needs
																				// to
																				// be
																				// reworked
	}

	@Override
	public String save() {
		return String.valueOf(powerGrid.ID);
	}

	@Override
	public PowerGridMemberType getPowerGridMemberType() {
		return PowerGridMemberType.CABLE;
	}

	@Override
	public int getPriority() {
		return 0;
	}

}
