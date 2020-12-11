package io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai;

public abstract class AIAction {

	public final AIAction parent;

	public AIAction(AIAction parent) {
		this.parent = parent;
	}

	/**
	 * Performs the action and returns the next AIAction.
	 * 
	 * @param profile
	 * @return
	 */
	public abstract AIAction runAction(AIProfile profile);

}
