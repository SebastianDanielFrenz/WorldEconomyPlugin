package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;

public abstract class ResearchItemChooserEvent {

	public abstract void event(ResearchItem researchItem);

	/**
	 * @param researchItem
	 * @return <b>true</b> if the item should be shown.
	 */
	public abstract boolean filter(ResearchItem researchItem);

}
