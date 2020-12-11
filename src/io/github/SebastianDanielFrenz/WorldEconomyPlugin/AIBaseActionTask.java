package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai.AIProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;

public class AIBaseActionTask extends Task {

	private AIProfile profile;

	public AIBaseActionTask(AIProfile profile) {
		this.profile = profile;
	}

	@Override
	public int getPriority() {
		return Integer.MAX_VALUE - 100;
	}

	@Override
	public void init() {
	}

	@Override
	public void work() {

	}

	@Override
	public void discard() {
	}

	@Override
	public boolean startOnShutdown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean continueOnShutdown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean discardOnOverload() {
		// TODO Auto-generated method stub
		return false;
	}

}
