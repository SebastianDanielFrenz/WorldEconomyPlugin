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

	public boolean hasResearched(ResearchItem item) {
		for (ResearchItem item2 : researched) {
			if (item == item2) {
				return true;
			}
		}
		return false;
	}

}
