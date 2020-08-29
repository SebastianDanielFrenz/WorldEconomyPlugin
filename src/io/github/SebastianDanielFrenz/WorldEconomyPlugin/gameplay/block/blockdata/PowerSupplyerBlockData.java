package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.CustomBlockDataCreationException;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric.PowerGridMemberType;

public class PowerSupplyerBlockData extends PowerConnectedBlockData {

	public PowerSupplyerBlockData(Location location) {
		super(location);
	}

	public PowerSupplyerBlockData(Location location, String raw) throws CustomBlockDataCreationException {
		super(location, raw);
	}

	@Override
	public PowerGridMemberType getPowerGridMemberType() {
		return PowerGridMemberType.SUPPLYER;
	}

	@Override
	public String save() {
		return null;
	}

}
