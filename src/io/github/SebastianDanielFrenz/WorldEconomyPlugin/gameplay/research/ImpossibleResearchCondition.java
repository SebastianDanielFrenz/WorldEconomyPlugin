package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research;

public class ImpossibleResearchCondition extends ResearchCondition {

	@Override
	public boolean isMet(long entityID, String entityType) {
		return false;
	}

}
