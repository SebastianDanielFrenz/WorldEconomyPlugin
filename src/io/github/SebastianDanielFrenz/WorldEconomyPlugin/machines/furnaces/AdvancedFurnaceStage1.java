package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Units;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.items.Tier;

public class AdvancedFurnaceStage1 extends AdvancedFurnace {

	public AdvancedFurnaceStage1(Location location) {
		super(location);
	}

	@Override
	public double getMaintenanceCost() {
		return 5;
	}

	@Override
	public double getMaintenanceFrequency() {
		return Units.HOUR * 2;
	}

	@Override
	public double getProcessTimeMultiplier() {
		return 1.25;
	}

	@Override
	public String getName() {
		return Tier.TIER2 + "Advanced Furnace Stage 1";
	}

}
