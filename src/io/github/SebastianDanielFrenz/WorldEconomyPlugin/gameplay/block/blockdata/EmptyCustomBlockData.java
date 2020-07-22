package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.CustomBlockDataCreationException;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;

public class EmptyCustomBlockData extends CustomBlockData {

	public EmptyCustomBlockData(String rawData) throws CustomBlockDataCreationException {
		super(rawData);
	}

	public EmptyCustomBlockData() throws CustomBlockDataCreationException {
		super();
	}

	@Override
	public String save() {
		return "";

	}

}
