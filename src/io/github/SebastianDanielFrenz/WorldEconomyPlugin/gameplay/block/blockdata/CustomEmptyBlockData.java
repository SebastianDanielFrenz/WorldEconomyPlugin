package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;

public class CustomEmptyBlockData extends CustomBlockData {

	public CustomEmptyBlockData(String rawData) {
		super(rawData);
	}

	@Override
	public String save() {
		return "";

	}

}
