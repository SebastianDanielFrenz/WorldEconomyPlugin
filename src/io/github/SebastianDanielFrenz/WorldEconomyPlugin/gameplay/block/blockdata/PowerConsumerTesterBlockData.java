package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.CustomBlockDataCreationException;

public class PowerConsumerTesterBlockData extends DefaultPowerConsumerBlockData {

	public PowerConsumerTesterBlockData(Location location) {
		super(location);
	}

	public PowerConsumerTesterBlockData(Location location, String rawData) throws CustomBlockDataCreationException {
		super(location, rawData);
	}

	@Override
	public String save() {
		return null;
	}

	private double power_recieved = 0;

	public void setPowerRecieved(double power) {
		power_recieved = power;
	}

	public double getPowerRecieved() {
		return power_recieved;
	}

}
