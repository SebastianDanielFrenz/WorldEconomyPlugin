package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.tasks;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;

public class BenchmarkTask extends Task {

	private int runs;
	private int current_runs = 0;
	private int slice = 10000;
	private int flos = slice * runs;
	private long start;

	private boolean done = false;

	public BenchmarkTask(int runs) {
		this.runs = runs;
	}

	@Override
	public int getPriority() {
		return -10000;
	}

	@Override
	public void init() {
		start = System.currentTimeMillis();
	}

	@Override
	public void work() {
		@SuppressWarnings("unused")
		float f = 1.1f;
		for (int i = 0; i < slice; i++) {
			f *= 1.1f;
		}
		current_runs++;

		if (runs == current_runs) {
			done = true;
			float time = (System.currentTimeMillis() - start) / 1000f;

			WorldEconomyPlugin.plugin.getLogger().info("[Benchmark]: " + flos + " flos in " + time + "s (" + (flos / time) + ")");
		}
	}

	@Override
	public void discard() {
	}

	@Override
	public boolean startOnShutdown() {
		return false;
	}

	@Override
	public boolean continueOnShutdown() {
		return false;
	}

	@Override
	public boolean hasFinished() {
		return done;
	}

	@Override
	public String getName() {
		return "Benchmarking Task";
	}

	@Override
	public boolean discardOnOverload() {
		return false;
	}

}
