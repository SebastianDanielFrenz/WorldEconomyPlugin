package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay;

public enum Age {

	OLD_STONE_AGE,
	MID_STONE_AGE(OLD_STONE_AGE),
	NEW_STONE_AGE(MID_STONE_AGE),
	COPPER_AGE(NEW_STONE_AGE),
	BRONZE_AGE(COPPER_AGE),
	IRON_AGE(BRONZE_AGE),;

	public final int index;

	private Age(Age prev) {
		index = prev.index + 1;
	}

	private Age() {
		index = 0;
	}

}
