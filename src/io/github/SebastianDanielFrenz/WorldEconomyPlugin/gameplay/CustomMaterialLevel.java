package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay;

public enum CustomMaterialLevel {

	HAND(0),
	WOOD(HAND),
	COBBLESTONE(WOOD),
	HARDENED_COBBLESTONE(COBBLESTONE),
	PROCESSED_COBBLESTONE(HARDENED_COBBLESTONE),
	GRANITE(PROCESSED_COBBLESTONE),
	DIORITE(GRANITE),
	ANDESITE(DIORITE),
	COPPER(ANDESITE);

	private CustomMaterialLevel(int lvl) {
		this.lvl = lvl;
	}

	public final int lvl;

	private CustomMaterialLevel(CustomMaterialLevel previous) {
		lvl = previous.lvl + 1;
	}

}