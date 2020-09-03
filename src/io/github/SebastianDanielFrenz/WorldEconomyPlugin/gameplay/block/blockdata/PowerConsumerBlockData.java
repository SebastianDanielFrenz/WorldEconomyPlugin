package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.CustomBlockDataCreationException;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric.PowerGridMemberType;

public abstract class PowerConsumerBlockData extends PowerConnectedBlockData {

	protected int priority;

	public PowerConsumerBlockData(Location location) {
		super(location);

		priority = 0;
	}

	public PowerConsumerBlockData(Location location, String rawData) throws CustomBlockDataCreationException {
		super(location, rawData);
	}

	@Override
	public PowerGridMemberType getPowerGridMemberType() {
		return PowerGridMemberType.CONSUMER;
	}

	@Override
	public int getPriority() {
		return priority;
	}

}
