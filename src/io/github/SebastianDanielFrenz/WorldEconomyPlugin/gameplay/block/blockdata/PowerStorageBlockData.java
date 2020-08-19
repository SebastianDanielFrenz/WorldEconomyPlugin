package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;

public class PowerStorageBlockData extends CustomBlockData {

	private double stored_power;

	public PowerStorageBlockData() {
		stored_power = 0;
	}

	public PowerStorageBlockData(String rawData) {
		stored_power = Double.parseDouble(rawData);
	}

	public double getStoredPower() {
		return stored_power;
	}

	public void setStoredPower(double stored) {
		stored_power = stored;
	}

	@Override
	public String save() {
		return String.valueOf(stored_power);
	}

}
