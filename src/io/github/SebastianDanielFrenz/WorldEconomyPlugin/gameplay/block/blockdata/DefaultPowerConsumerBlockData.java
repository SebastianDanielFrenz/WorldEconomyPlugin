package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.CustomBlockDataCreationException;

public abstract class DefaultPowerConsumerBlockData extends PowerConsumerBlockData {

	public DefaultPowerConsumerBlockData(Location location) {
		super(location);
	}

	public DefaultPowerConsumerBlockData(Location location, String rawData) throws CustomBlockDataCreationException {
		super(location, rawData);
	}

	private double tmp_power;

	public void resetTmpPower() {
		tmp_power = 0;
	}

	public void addTmpPower(double amount) {
		tmp_power += amount;
	}

	public double getTmpPower() {
		return tmp_power;
	}

	private boolean powered = false;

	public void setPowered(boolean state) {
		powered = state;
	}

	public boolean isPowered() {
		return powered;
	}

}
