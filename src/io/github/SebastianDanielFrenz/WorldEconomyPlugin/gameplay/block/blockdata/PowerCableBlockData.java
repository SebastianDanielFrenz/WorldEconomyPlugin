package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;

public class PowerCableBlockData extends CustomBlockData {

	private long powerGridID;

	public PowerCableBlockData() {
		powerGridID = PowerGridRegistry.lastPowerGridID + 1;
		PowerGridRegistry.lastPowerGridID++;
	}

	public PowerCableBlockData(String rawData) {
		powerGridID = Long.parseLong(rawData);
	}

	public void setPowerGridID(long ID) {
		powerGridID = ID;
	}

	public long getPowerGridID() {
		return powerGridID;
	}

	@Override
	public String save() {
		return String.valueOf(powerGridID);
	}

}
