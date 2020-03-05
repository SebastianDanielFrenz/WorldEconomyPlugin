package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.research;

import java.util.List;

public class ResearchProfile {

	public List<ResearchItem> researched;
	public long companyID;

	public ResearchProfile(List<ResearchItem> researched, long companyID) {
		this.researched = researched;
		this.companyID = companyID;
	}

}
