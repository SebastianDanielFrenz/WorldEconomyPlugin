package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research;

import java.util.List;

public class ResearchProfile {

	public List<ResearchItem> researched;
	public long entityID;
	public String entityType;

	public ResearchProfile(List<ResearchItem> researched, long entityID, String entityType) {
		this.researched = researched;
		this.entityID = entityID;
		this.entityType = entityType;
	}

}
