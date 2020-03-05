package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.research;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.Machine;

public abstract class ResearchItem {

	public abstract ResearchItem[] getParents();

	public abstract Machine getMachine();

}