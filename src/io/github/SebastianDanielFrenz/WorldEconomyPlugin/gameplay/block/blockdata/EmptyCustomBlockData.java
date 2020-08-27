package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.CustomBlockDataCreationException;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;

public class EmptyCustomBlockData extends CustomBlockData {

	public EmptyCustomBlockData(Location location, String rawData) throws CustomBlockDataCreationException {
		super(location, rawData);
	}

	public EmptyCustomBlockData(Location location) throws CustomBlockDataCreationException {
		super(location);
	}

	@Override
	public String save() {
		return "";

	}

}
