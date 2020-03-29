package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research;

public class ExperienceResearchCondition extends ResearchCondition {

	public ExperienceResearchCondition(ExperienceCounter counter, double min) {
		this.counter = counter;
		this.min = min;
	}

	private ExperienceCounter counter;
	private double min;

	@Override
	public boolean isMet(long entityID, String entityType) {
		if (entityType.equals("player") || entityType.equals("ai")) {
			return counter.getXP(entityID, entityType) >= min;
		} else {
			throw new RuntimeException("Companies should never have to use experience research conditions!");
		}
	}

}
