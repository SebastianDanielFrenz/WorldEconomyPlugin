package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines;

public enum CustomMaterialLevel {

	WOOD(1),
	COBBLESTONE(WOOD),
	HARDENED_COBBLESTONE(COBBLESTONE),
	PROCESSED_COBBLESTONE(HARDENED_COBBLESTONE),
	STONE(PROCESSED_COBBLESTONE),
	COPPER(STONE);

	private CustomMaterialLevel(int lvl) {
		this.lvl = lvl;
	}

	public final int lvl;

	private CustomMaterialLevel(CustomMaterialLevel previous) {
		lvl = previous.lvl + 1;
	}

}