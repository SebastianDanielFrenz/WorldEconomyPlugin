package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.campfires;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Units;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;

public class MachineEgyptianCampfireStage1 extends MachineEgyptianCampfire {

	public MachineEgyptianCampfireStage1(Location location) {
		super(location);
	}

	@Override
	public double getMaintenanceCost() {
		return 1;
	}

	@Override
	public double getMaintenanceFrequency() {
		return Units.HOUR;
	}

	@Override
	public double getProcessTimeMultiplier() {
		return 1;
	}

	@Override
	public String getName() {
		return CustomItemRegistry.EGYPTIAN_CAMPFIRE_STAGE1.getEffectiveItemName();
	}

}
