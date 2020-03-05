package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.blast_furnaces;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Units;

public class BasicBlastFurnaceStage1 extends BasicBlastFurnace {

	public BasicBlastFurnaceStage1(Location location) {
		super(location);
	}

	@Override
	public double getMaintenanceCost() {
		return 2.5;
	}

	@Override
	public double getMaintenanceFrequency() {
		return Units.MINUTE * 10;
	}

	@Override
	public double getProcessTimeMultiplier() {
		return 1;
	}

}
