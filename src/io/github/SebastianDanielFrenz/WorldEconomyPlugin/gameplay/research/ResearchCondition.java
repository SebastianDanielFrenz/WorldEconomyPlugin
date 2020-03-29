package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research;

public abstract class ResearchCondition {

	/**
	 * The entityID is an empoyeeID for employees and a companyID for companies.
	 * 
	 * @param entityID
	 * @param entityType
	 * @return
	 */
	public abstract boolean isMet(long entityID, String entityType);
}
