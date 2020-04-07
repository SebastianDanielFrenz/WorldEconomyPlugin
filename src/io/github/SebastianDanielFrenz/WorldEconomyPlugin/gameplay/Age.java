package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay;

public enum Age {

	OLD_STONE_AGE,
	MID_STONE_AGE(OLD_STONE_AGE),
	NEW_STONE_AGE(MID_STONE_AGE),
	COPPER_AGE(NEW_STONE_AGE),
	ANCIENT_EGYPT(COPPER_AGE),
	ANCIENT_GREECE(ANCIENT_EGYPT),
	ANCIENT_ROME(ANCIENT_GREECE),
	EALRY_MIDDLE_AGES(ANCIENT_ROME),
	MIDDLE_AGES(EALRY_MIDDLE_AGES),
	LATE_MIDDLE_AGES(MIDDLE_AGES);

	public final int index;

	private Age(Age prev) {
		index = prev.index + 1;
	}

	private Age() {
		index = 0;
	}

}
