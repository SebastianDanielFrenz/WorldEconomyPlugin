package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machines.campfires;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Units;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;

public class BlockEgyptianCampfireStage1 extends MachineEgyptianCampfire {

	public BlockEgyptianCampfireStage1() {
		super("egyptian_campfire_stage1", easyDrop(CustomItemRegistry.EGYPTIAN_CAMPFIRE_STAGE1));
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
