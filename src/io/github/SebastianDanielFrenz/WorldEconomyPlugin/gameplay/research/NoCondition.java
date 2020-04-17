package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research;

public class NoCondition extends ResearchCondition {

	@Override
	public boolean isMet(long entityID, String entityType) {
		return true;
	}

}
