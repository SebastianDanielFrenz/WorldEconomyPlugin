package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.CustomBlockDataCreationException;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;

public abstract class PowerStorageBlockData extends CustomBlockData {

	protected double stored_power;

	public PowerStorageBlockData(Location location) {
		super(location);
		stored_power = 0;
	}

	public PowerStorageBlockData(Location location, String rawData) throws CustomBlockDataCreationException {
		super(location, rawData);
	}

	public double getStoredPower() {
		return stored_power;
	}

	public void setStoredPower(double stored) {
		stored_power = stored;
	}

}
