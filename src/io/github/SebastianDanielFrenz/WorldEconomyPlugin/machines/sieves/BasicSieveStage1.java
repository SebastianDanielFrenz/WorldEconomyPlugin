package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.sieves;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Units;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.items.Tier;

public class BasicSieveStage1 extends BasicSieve {

	public BasicSieveStage1(Location location) {
		super(location);
	}

	@Override
	public String getName() {
		return Tier.TIER1 + "Basic Sieve Stage 1";
	}

	@Override
	public double getMaintenanceCost() {
		return 2;
	}

	@Override
	public double getMaintenanceFrequency() {
		return Units.HOUR * 2;
	}

	@Override
	public double getProcessTimeMultiplier() {
		return 1;
	}

}
