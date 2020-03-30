package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.CustomBlockDataCreationException;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;

public class CustomEmptyBlockData extends CustomBlockData {

	public CustomEmptyBlockData(String rawData) throws CustomBlockDataCreationException {
		super(rawData);
	}

	public CustomEmptyBlockData() throws CustomBlockDataCreationException {
		super();
	}

	@Override
	public String save() {
		return "";

	}

}
